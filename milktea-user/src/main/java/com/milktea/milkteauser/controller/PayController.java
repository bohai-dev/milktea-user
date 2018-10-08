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
    public String  iotNotify(IotResponseBean responseBean){

        LOGGER.info("支付通知"+responseBean.toString());
        String result="";
        try {
            result=payInfoService.iotNotify(responseBean);
        } catch (MilkTeaException e) {
            e.printStackTrace();
            result="fail";
        }

        return result;

    }

}
