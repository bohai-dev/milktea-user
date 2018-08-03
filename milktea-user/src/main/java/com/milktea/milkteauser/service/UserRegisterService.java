package com.milktea.milkteauser.service;

import java.util.List;


import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;




public interface UserRegisterService {
	
	/**
     * 认证用户信息
     * @param figureVo
     * @throws MilkTeaException
     */
   public TeaUserInfo findRegister(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
   
   
   
}
