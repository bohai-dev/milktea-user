package com.milktea.milkuser.com.milktea.milkteauser.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.milktea.milkteauser.service.SmsService;
import com.milktea.milkteauser.vo.ResponseBody;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SmsServiceTest {
   @Autowired
   SmsService smsService;
   
   @Test
   public void sendSms(){
	   
	   ResponseBody<String> res= smsService.sendVerCodeSMS("17621503621","SMS_134326005");
	   System.out.println(res);
   }
}
