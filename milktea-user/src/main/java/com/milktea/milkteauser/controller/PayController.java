package com.milktea.milkteauser.controller;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.PayInfoService;
import com.milktea.milkteauser.vo.IOTBean;
import com.milktea.milkteauser.vo.ResponseHeader;
import com.milktea.milkteauser.vo.StripeBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

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
    public void iotPay(@RequestBody IOTBean iotBean){

        payInfoService.iotPay(iotBean);
    }

    @RequestMapping("/iotpay/notify")
    public void  notify(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        LOGGER.info("Iotpay异步接收通知");
        StringBuilder sb = new StringBuilder();
        try {
            out=response.getWriter();
            BufferedReader reader=request.getReader();
            String line=null;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            LOGGER.info("Iotpay接收到消息："+sb.toString());
            //需要验证签名


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
