package com.milktea.milkuser.com.milktea.milkteauser.utils;

import com.milktea.milkteauser.Application;
import com.milktea.milkteauser.dao.TeaPayInfoMapper;
import com.milktea.milkteauser.domain.TeaPayInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.milktea.milkteauser.service.SmsService;
import com.milktea.milkteauser.vo.ResponseBody;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SmsServiceTest {

   @Autowired
   SmsService smsService;

    @Autowired
    TeaPayInfoMapper teaPayInfoMapper;
   
   @Test
   public void sendSms(){
	   
	   ResponseBody<String> res= smsService.sendVerCodeSMS("17621503621","SMS_111715045");
	   System.out.println(res.getData());
   }

   @Test
    public void  insertPayRecord(){
       TeaPayInfo teaPayInfo=new TeaPayInfo();
       teaPayInfo.setPayId(teaPayInfoMapper.generateClassId());
       teaPayInfo.setOrderNo("0000000002");
       teaPayInfo.setPayStatus("0");
       teaPayInfo.setPayTime(new Date());
       teaPayInfoMapper.insertSelective(teaPayInfo);
   }
}
