package com.milktea.milkteauser.service.impl;


import java.util.Random;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.milktea.milkteauser.exception.MilkTeaErrorConstant;
import com.milktea.milkteauser.service.SmsService;
import com.milktea.milkteauser.vo.ResponseBody;



@Service
public class SmsServiceImpl implements SmsService {
	
	//初始化ascClient需要的几个参数
	 String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
	 String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
			
	//替换成你的AK
	 String accessKeyId = "LTAIJsHDuEGbjkTy";//你的accessKeyId,参考本文档步骤2
	 String accessKeySecret = "ggk7r1WoKRSofcJmgzrql3nsuLvHrn";//你的accessKeySecret，参考本文档步骤2

	/**
	 * 发送验证码类短信
	 */
	@Override
	public ResponseBody<String> sendVerCodeSMS(String phoneNumber, String templateCode) {
		
		//SMS_134326005
		ResponseBody<String> responseBean=new ResponseBody<String>();
		
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");		
		
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //使用post提交
		 request.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
		 request.setPhoneNumbers(phoneNumber);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName("乔升科技");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode(templateCode);
		 
		
	    //4位验证码
		 String returnStr = "";
	    long t = System.currentTimeMillis();
		Random r1 = new Random(t);
		int i;
		i=r1.nextInt(10);
		returnStr = returnStr + String.valueOf(i);
		i=r1.nextInt(10);
		returnStr = returnStr + String.valueOf(i);
		i=r1.nextInt(10);
		returnStr = returnStr + String.valueOf(i);
		i=r1.nextInt(10);
		returnStr = returnStr + String.valueOf(i);
		 String code=returnStr;
		 JSONObject param=new JSONObject();
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		param.put("code", code);
		request.setTemplateParam(param.toString());
		 
		 
		 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		 //request.setSmsUpExtendCode("90997");
		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		// request.setOutId("yourOutId");
		 
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				//请求成功
				responseBean.setData(code);
			}else {
				
				 responseBean.setRspCode(MilkTeaErrorConstant.SMS_FAILURE.getErrorCode());
			     responseBean.setCnErrorMsg(MilkTeaErrorConstant.SMS_FAILURE.getCnErrorMsg());
			}
		} catch (ClientException e) {
			 e.printStackTrace();
			
		     responseBean.setRspCode(MilkTeaErrorConstant.SMS_RESPONSE_FAILURE.getErrorCode());
		     responseBean.setCnErrorMsg(e.getErrMsg());
		}
		
		return responseBean;
	}
	
	/**
	 * 发送通知类类短信，可携带多个参数
	 */
	@Override
	public ResponseBody<Integer> sendNotificationSMS(String phoneNumber, String templateCode,String params) {
		
		ResponseBody<Integer> responseBean=new ResponseBody<Integer>();
		
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");		
		
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		} catch (ClientException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //使用post提交
		 request.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
		 request.setPhoneNumbers(phoneNumber);
		 //必填:短信签名-可在短信控制台中找到
		 // request.setSignName("乔升科技");
		  request.setSignName("creativefun");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode(templateCode);		 
		
	
	    request.setTemplateParam(params);		 

		 
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				//请求成功
				responseBean.setData(1);
			}else {
				
				 responseBean.setRspCode(MilkTeaErrorConstant.SMS_FAILURE.getErrorCode());
			     responseBean.setCnErrorMsg(MilkTeaErrorConstant.SMS_FAILURE.getCnErrorMsg());
			}
		} catch (ClientException e) {
			 e.printStackTrace();
			
		     responseBean.setRspCode(MilkTeaErrorConstant.SMS_RESPONSE_FAILURE.getErrorCode());
		     responseBean.setCnErrorMsg(e.getErrMsg());
		}
		
		return responseBean;
	}

}
