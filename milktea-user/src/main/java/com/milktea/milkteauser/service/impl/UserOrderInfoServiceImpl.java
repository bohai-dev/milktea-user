package com.milktea.milkteauser.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteauser.dao.TeaUserInfoMapper;
import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserOrderInfoService;
import com.milktea.milkteauser.service.UserRegisterService;
import com.milktea.milkteauser.vo.CustOrderInfoVo;


@Service("userOrderInfoService")
public  class UserOrderInfoServiceImpl implements UserOrderInfoService {

	@Override
	public Integer userOrderOper(CustOrderInfoVo custOrderInfoVo) throws MilkTeaException {
		// TODO Auto-generated method stub
		return null;
	}



	
    
}
