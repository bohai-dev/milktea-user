package com.milktea.milkteauser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@RequestMapping("/wxpay")
public class WxPayController {

    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(WxPayController.class);

    //异步接收微信支付通知
    public void  notify(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        LOGGER.info("微信支付异步接收通知");
        StringBuilder sb = new StringBuilder();
        try {
            out=response.getWriter();
            BufferedReader reader=request.getReader();
            String line=null;
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
            LOGGER.info("微信支付接收到消息："+sb.toString());
            //需要验证签名


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
