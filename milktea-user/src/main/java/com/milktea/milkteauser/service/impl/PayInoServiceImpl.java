package com.milktea.milkteauser.service.impl;

import com.milktea.milkteauser.dao.TeaPayInfoMapper;
import com.milktea.milkteauser.domain.TeaPayInfo;
import com.milktea.milkteauser.exception.MilkTeaErrorConstant;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.PayInfoService;
import com.milktea.milkteauser.vo.ResponseBody;
import com.milktea.milkteauser.vo.StripeBean;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class PayInoServiceImpl implements PayInfoService {
    @Autowired
    TeaPayInfoMapper mapper;
    @Autowired
    UserOrderInfoServiceImpl orderService;
    private static final Logger LOGGER = LoggerFactory.getLogger(PayInoServiceImpl.class);
  //  private static final String STRIPE_KEY="sk_live_dNCjtQTOeP6W4hn9b93sKDVK";   正式key
    private static final String STRIPE_KEY="sk_test_yb8n1W1TWPZwhdZ6Su0vSVWt";    //测试key

    public  void  stripePay(StripeBean stripeBean) throws MilkTeaException{
      //  ResponseBody<String> responseBody=new ResponseBody<>();

        TeaPayInfo teaPayInfo=new TeaPayInfo();
        teaPayInfo.setPayId(mapper.generateClassId());
        teaPayInfo.setOrderNo(stripeBean.getOrderNum());   //订单编号
        teaPayInfo.setPayType("stripe");
        teaPayInfo.setPayTime(new Date());

        Stripe.apiKey = STRIPE_KEY;
        String token = stripeBean.getToken();

        LOGGER.info("获取到token："+token);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", stripeBean.getAmount());         //充值金额，单位分
        params.put("currency", "usd");   //货币类型 usd美元
        params.put("description", stripeBean.getDescription());  //充值描述
        params.put("source", token);
        try {
            //发起支付
            Charge charge = Charge.create(params);
            //充值成功，处理业务逻辑
            //更新订单  支付状态 1 成功
            orderService.modifyOrderStatus(stripeBean.getOrderNum(),"1");

            teaPayInfo.setPaySerialNo(charge.getId());
            teaPayInfo.setPayStatus("1");                    //支付状态 1支付成功 2支付失败

            //TODO:通知订单后台，有新新订单

        } catch (Exception e) {
            LOGGER.error("支付发生错误:"+e.getMessage(),e);
            teaPayInfo.setPayStatus("2");
            teaPayInfo.setErrorMsg(e.getMessage());
            //更新订单  支付状态 2 失败
            orderService.modifyOrderStatus(stripeBean.getOrderNum(),"2");
            throw  new MilkTeaException(MilkTeaErrorConstant.PAY_FAIL.getErrorCode(),e.getMessage(),e.getMessage(),e);
        }finally {
            //无论支付成功还是失败，都插入支付记录
            mapper.insertSelective(teaPayInfo);

        }


    }

}
