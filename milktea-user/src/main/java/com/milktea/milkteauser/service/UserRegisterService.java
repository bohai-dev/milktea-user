package com.milktea.milkteauser.service;

import java.util.List;


import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;




public interface UserRegisterService {
	
	/**
     * 认证用户信息WEIXINOPENID
     * @param figureVo
     * @throws MilkTeaException
     */
   public TeaUserInfo findRegisterOpenid(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
   /**
    * 认证用户信息TELEPHONE
    * @param figureVo
    * @throws MilkTeaException
    */
  public TeaUserInfo findRegisterTelephone(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
   
    /**
    * 绑定手机和微信OPENID
	 * @param teaUserInfo
	 * @throws MilkTeaException
    */
	public void bindOpenidTel(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
	/**
     * 绑定手机和微信OPENID
	 * @param teaUserInfo
	 * @throws MilkTeaException
	*/
	public String getPollCode(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
   
}
