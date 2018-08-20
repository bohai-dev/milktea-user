package com.milktea.milkteauser.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteauser.dao.TeaLoginWeixinMapper;
import com.milktea.milkteauser.domain.TeaLoginWeixin;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserLoginService;


@Service("userLoginService")
public  class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	TeaLoginWeixinMapper teaLoginWeixinMapper;
	
	static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Override
	public Integer insert(TeaLoginWeixin teaLoginWeixin) throws MilkTeaException {
		
		TeaLoginWeixin teaLoginWeixintemp = new TeaLoginWeixin();
		teaLoginWeixintemp = this.selectByopenId(teaLoginWeixin.getWeixinOpenid());
		if(null == teaLoginWeixintemp){
			//如果openid不存在则插入
			teaLoginWeixinMapper.insertSelective(teaLoginWeixin);
		} else {
			//如果openID已经存在则更新
			teaLoginWeixinMapper.updateByPrimaryKey(teaLoginWeixin);
		}
		
		return 1;
	}

	@Override
	public TeaLoginWeixin selectByopenId(String openId) throws MilkTeaException {
		//openID查询
		TeaLoginWeixin teaLoginWeixintemp = new TeaLoginWeixin();
		teaLoginWeixintemp = teaLoginWeixinMapper.selectByPrimaryKey(openId);
		return teaLoginWeixintemp;
		
	}

	@Override
	public Integer update(TeaLoginWeixin teaLoginWeixin) throws MilkTeaException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
    
}
