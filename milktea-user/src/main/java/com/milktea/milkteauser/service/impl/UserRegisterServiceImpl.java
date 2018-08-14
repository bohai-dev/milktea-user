package com.milktea.milkteauser.service.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteauser.dao.TeaSmsRegisterMapper;
import com.milktea.milkteauser.dao.TeaUserInfoMapper;
import com.milktea.milkteauser.domain.TeaSmsRegister;
import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.SmsService;
import com.milktea.milkteauser.service.UserRegisterService;
import com.milktea.milkteauser.vo.ResponseBody;


@Service("userRegisterService")
public  class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	TeaUserInfoMapper TeaUserInfoMapper;
	
	@Autowired
	TeaSmsRegisterMapper teaSmsRegisterMapper;
	
	@Autowired
	SmsService smsService;
	
	static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);
	
	@Override
	public TeaUserInfo findRegisterOpenid(String weixinOpenid) throws MilkTeaException {
		//用户是否已经注册，及是否已经能取得正确的手机号
		TeaUserInfo teaUserInfoRet = new TeaUserInfo () ;
	
		//微信登入的场合返回客户已经登入的信息，如果返回空则说明没有注册过
		teaUserInfoRet = TeaUserInfoMapper.selectByWeixinOpenId(weixinOpenid);

		return teaUserInfoRet;
		
	}

	@Override
	public void bindOpenidTel(TeaUserInfo teaUserInfo) throws MilkTeaException {
		
		//当手机号已经存在时 新的微信号和手机号绑定
		TeaUserInfoMapper.bindTelephoneWeixinOpenid(teaUserInfo.getTelephone(),teaUserInfo.getWeixinOpenid());
		
	}

	@Override
	public TeaUserInfo findRegisterTelephone(String telephone) throws MilkTeaException {
		//用户是否已经注册，及是否已经能取得正确的手机号
		TeaUserInfo teaUserInfoRet = new TeaUserInfo () ;
				
			
		//已经有了手机号 则直接返回详细信息，如果返回NULL则说明没有注册过
		teaUserInfoRet = TeaUserInfoMapper.selectByTelephone(telephone);
					
		return teaUserInfoRet;
	}

	@Override
	public int createPollCode(String telephone) throws MilkTeaException {

		//调用阿里云短信发送
		// TODO: 调用阿里云短信发送 参数 电话号码 验证码为随机数
		//SMS_134326005 是认证模板号
		ResponseBody<String> responseStr = new ResponseBody<String>();
		//海外
//		responseStr = smsService.sendVerCodeSMS(telephone,"SMS_134326005");
		//国内
		responseStr = smsService.sendVerCodeSMS(telephone,"SMS_111715045");
		
		//写入数据库
		teaSmsRegisterMapper.insertSMSReg(telephone, responseStr.getData());
		
		return 1;
		
	}

	@Override
	public int comparePollCode(TeaSmsRegister teaSmsRegister) throws MilkTeaException {
		int retInt =0;
		//比较验证码是否正确
		String CodeStr = teaSmsRegisterMapper.getIdentifyCode(teaSmsRegister.getTelephone());
		if(!teaSmsRegister.getIdentifyCode().equals(CodeStr)){
			//验证码不一致
			return 99;
		}
		
		//60分钟过期 测试时为2分钟
		retInt = teaSmsRegisterMapper.checkIdentifyTime(teaSmsRegister.getTelephone());
		
		
		return retInt;
	}

	@Override
	public TeaUserInfo userLogin(String telephone, String userPassword) throws MilkTeaException {
		TeaUserInfo teaUserInfo = new TeaUserInfo();
		
		teaUserInfo = TeaUserInfoMapper.checkUserLogin(telephone,userPassword);
		return teaUserInfo;
	}



	
    
}
