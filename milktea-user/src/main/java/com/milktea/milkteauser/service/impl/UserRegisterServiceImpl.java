package com.milktea.milkteauser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteauser.dao.TeaUserInfoMapper;
import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserRegisterService;


@Service("userRegisterService")
public  class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	TeaUserInfoMapper TeaUserInfoMapper;
	
	static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);
	
	@Override
	public TeaUserInfo findRegister(TeaUserInfo teaUserInfo) throws MilkTeaException {
		//用户是否已经注册，及是否已经能取得正确的手机号
		TeaUserInfo teaUserInfoRet = new TeaUserInfo () ;
		
		if(null != teaUserInfo.getTelephone()){
			//已经有了手机号 则直接返回详细信息，如果返回NULL则说明没有注册过
			teaUserInfoRet = TeaUserInfoMapper.selectByTelephone(teaUserInfo.getTelephone());
			return teaUserInfoRet;
		}
		if(null != teaUserInfo.getWeixinOpenid()){
			//微信登入的场合返回客户已经登入的信息，如果返回空则说明没有注册过
			teaUserInfoRet = TeaUserInfoMapper.selectByWeixinOpenId(teaUserInfo.getWeixinOpenid());
		}
		
		
		return teaUserInfoRet;
		
	}



	
    
}
