package com.milktea.milkteauser.controller;
import com.milktea.milkteauser.domain.TeaPayInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.PayInfoService;
import com.milktea.milkteauser.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@RestController
public class PayController {
    /** logger */
    private final Logger LOGGER = LoggerFactory.getLogger(PayController.class);

    @Autowired
    PayInfoService payInfoService;

    @RequestMapping("/stripe/charge")
    public  ResponseHeader setCharge(@RequestBody StripeBean stripeBean) throws MilkTeaException {
        LOGGER.info("接收到请求");
        payInfoService.stripePay(stripeBean);
        ResponseHeader responseHeader=new ResponseHeader();
        return responseHeader;
    }
    @RequestMapping("/iotpay/charge")
    public ResponseBody<String> iotPay(@RequestBody IOTBean iotBean) throws MilkTeaException{

        return payInfoService.iotPay(iotBean);
    }

    

    @RequestMapping("/iotpay/notify")
    public void  iotNotify(HttpServletRequest request, HttpServletResponse response){

       LOGGER.info("iot支付异步接收通知");
       String payOrderId=request.getParameter("payOrderId");
       String mchOrderNo=request.getParameter("mchOrderNo");
       String status=request.getParameter("status");
       String channelId=request.getParameter("channelId");
       String param1=request.getParameter("param1");

       IotResponseBean responseBean=new IotResponseBean();
       responseBean.setPayOrderId(payOrderId);
       responseBean.setMchOrderNo(mchOrderNo);
       responseBean.setStatus(Integer.parseInt(status));
       responseBean.setChannelId(channelId);
       responseBean.setParam1(param1);

       String result="fail";
       PrintWriter out =null;
       try {
           out=response.getWriter();
           result=payInfoService.iotNotify(responseBean);
           out.print(result);
       } catch (Exception e) {
           e.printStackTrace();
           result="fail";
           out.print(result);
       }finally {
           if (out!=null){
               out.close();
           }
       }

    }
    

}
