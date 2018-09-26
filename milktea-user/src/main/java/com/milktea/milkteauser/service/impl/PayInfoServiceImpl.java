package com.milktea.milkteauser.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.milktea.milkteauser.util.Utils;
import com.milktea.milkteauser.vo.IOTBean;
import com.milktea.milkteauser.vo.IotResponseBean;
import com.milktea.milkteauser.vo.ResponseBody;
import com.milktea.milkteauser.wxpay.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteauser.dao.TeaPayInfoMapper;
import com.milktea.milkteauser.domain.TeaOrderInfo;
import com.milktea.milkteauser.domain.TeaPayInfo;
import com.milktea.milkteauser.exception.MilkTeaErrorConstant;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.PayInfoService;
import com.milktea.milkteauser.service.UserInfoService;
import com.milktea.milkteauser.service.UserOrderInfoService;
import com.milktea.milkteauser.util.HttpUtil;
import com.milktea.milkteauser.vo.StripeBean;
import com.stripe.Stripe;
import com.stripe.model.Charge;
import org.springframework.web.bind.annotation.RequestBody;
import sun.rmi.runtime.Log;

@Service
public class PayInfoServiceImpl implements PayInfoService {
    @Autowired
    TeaPayInfoMapper mapper;
    @Autowired
    UserOrderInfoService orderService;
    @Autowired
    UserInfoService userInfoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PayInfoServiceImpl.class);
    private static final String STRIPE_KEY = "sk_live_dNCjtQTOeP6W4hn9b93sKDVK";   //正式key
    // private static final String STRIPE_KEY="sk_test_yb8n1W1TWPZwhdZ6Su0vSVWt";    //测试key

    private static final String NOTIFY_ORDER_URL = "http://localhost:8081/handleOrder";  //后台推送订单url

    private static final String IOTPAY_URL = "https://pay.4jicao.com/api/pay/create_order";

    private static final String IOT_KEY = "hsN3Nge1KPtiVdL5zK9s3PKJAIId5Hrh";

    public void stripePay(StripeBean stripeBean) throws MilkTeaException {


        TeaPayInfo teaPayInfo = new TeaPayInfo();
        teaPayInfo.setPayId(mapper.generateClassId());
        teaPayInfo.setOrderNo(stripeBean.getOrderNum());   //订单编号
        teaPayInfo.setPayType("stripe");
        teaPayInfo.setPayTime(new Date());

        Stripe.apiKey = STRIPE_KEY;
        String token = stripeBean.getToken();

        LOGGER.info("获取到token：" + token);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", stripeBean.getAmount());         //充值金额，单位分
        params.put("currency", "cad");   //货币类型 usd美元
        params.put("description", stripeBean.getDescription());  //充值描述
        params.put("source", token);
        try {
            //发起支付
            Charge charge = Charge.create(params);
            //充值成功，处理业务逻辑
            //更新订单  支付状态 1 成功
            orderService.updatePayStatus(stripeBean.getOrderNum(), "1");

            teaPayInfo.setPaySerialNo(charge.getId());
            teaPayInfo.setPayStatus("1");                    //支付状态 1支付成功 2支付失败

            //通知订单后台，有新订单
            Map<String, String> map = new HashMap<>();
            map.put("orderNo", stripeBean.getOrderNum());
            map.put("messageType", "0");
            String response = HttpUtil.post(NOTIFY_ORDER_URL, map);

            //更新积分 得到客户信息 积分就是支付金额
            TeaOrderInfo teaOrderInfo = new TeaOrderInfo();
            teaOrderInfo = orderService.findOrderByOrderNo(stripeBean.getOrderNum());
            userInfoService.modifyPoint(teaOrderInfo.getUserNo(), teaOrderInfo.getOrderPrice());


        } catch (Exception e) {
            LOGGER.error("支付发生错误:" + e.getMessage(), e);
            teaPayInfo.setPayStatus("2");
            teaPayInfo.setErrorMsg(e.getMessage());
            //更新订单  支付状态 2 失败
            orderService.updatePayStatus(stripeBean.getOrderNum(), "2");
            throw new MilkTeaException(MilkTeaErrorConstant.PAY_FAIL.getErrorCode(), e.getMessage(), e.getMessage(), e);
        } finally {
            //无论支付成功还是失败，都插入支付记录
            mapper.insertSelective(teaPayInfo);

        }


    }

    public ResponseBody<String> iotPay(IOTBean iotBean) throws MilkTeaException{
        ResponseBody<String> responseBody=new ResponseBody<>();
        try {
            Map<String, Object> map = Utils.objectToMap(iotBean);
            Map<String,String> paramsMap=new HashMap<>();
            map.forEach((k,v)->{
                if (v!=null){
                    paramsMap.put(k,v.toString());
                }else {
                    paramsMap.put(k,null);
                }
            });
            //获取签名
            String signature = WXPayUtil.generateSignature(paramsMap, IOT_KEY);
            map.put("sign", signature);

            Gson gson = new Gson();
            String json = gson.toJson(map);
            Map<String,String> params=new HashMap<>();
            params.put("params",json);
            String response = HttpUtil.postForm(IOTPAY_URL, params);

            responseBody.setData(response);

            LOGGER.info("支付结果:"+response);
        } catch (Exception e) {
           // e.printStackTrace();
            throw new MilkTeaException(MilkTeaErrorConstant.PAY_FAIL.getErrorCode(), e.getMessage(), e.getMessage(), e);

        }

        return responseBody;


    }

    public String iotNotify(IotResponseBean iotResponseBean) throws MilkTeaException{
        TeaPayInfo teaPayInfo = new TeaPayInfo();
        String result=null;

        //商户单号
        String selfOrderNum=iotResponseBean.getMchOrderNo().split("@")[0];
        //支付单号
        String payOrderNum=iotResponseBean.getPayOrderId();
        //支付状态,0-订单生成,1-支付中,2-支付成功,3-业务处理完成
        int payStatus=iotResponseBean.getStatus();
        teaPayInfo.setPayId(mapper.generateClassId());
        teaPayInfo.setOrderNo(selfOrderNum);
        teaPayInfo.setPaySerialNo(payOrderNum);
        teaPayInfo.setPayType("iotpay");
        teaPayInfo.setPayTime(new Date());
        try {
            if (payStatus==2){  //支付成功
                teaPayInfo.setPayStatus(1+"");
                //更新订单状态
                orderService.updatePayStatus(selfOrderNum, "1");
                //推送订单
                Map<String, String> map = new HashMap<>();
                map.put("orderNo", selfOrderNum);
                map.put("messageType", "0");
                String response = HttpUtil.post(NOTIFY_ORDER_URL, map);
                result="success";


            }else{

                teaPayInfo.setPayStatus(3+"");
                //更新订单状态 2失败
                orderService.updatePayStatus(selfOrderNum, "2");
                result="fail";

            }
            mapper.insertSelective(teaPayInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new MilkTeaException(MilkTeaErrorConstant.PAY_FAIL.getErrorCode(), e.getMessage(), e.getMessage(), e);
        }

        return result;

    }

}
