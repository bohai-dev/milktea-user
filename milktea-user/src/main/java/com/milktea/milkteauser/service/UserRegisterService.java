package com.milktea.milkteauser.service;

import com.milktea.milkteauser.domain.TeaLoginWeixin;
import com.milktea.milkteauser.domain.TeaSmsRegister;
import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;




public interface UserRegisterService {
	
	/**
     * 认证用户信息WEIXINOPENID
     * @param figureVo
     * @throws MilkTeaException
     */
   public TeaUserInfo findRegisterOpenid(String weixinOpenid) throws MilkTeaException;
   
   /**
    * 认证用户信息TELEPHONE
    * @param figureVo
    * @throws MilkTeaException
    */
  public TeaUserInfo findRegisterTelephone(String telephone) throws MilkTeaException;
   
   
    /**
    * 绑定手机和微信OPENID
	 * @param teaUserInfo
	 * @throws MilkTeaException
    */
	public void bindOpenidTel(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
	/**
     * 生成SMS验证码
	 * @param teaUserInfo
	 * @throws MilkTeaException
	*/
	public int createPollCode(String telephone) throws MilkTeaException;
	
	
	/**
     * 对比SMS验证码
	 * @param teaUserInfo
	 * @throws MilkTeaException
	*/
	public int comparePollCode(TeaSmsRegister teaSmsRegister) throws MilkTeaException;
	
	/**
	 * 用户登入CHECK
	 * @param telephone
	 * @param userPassword
	 * @return
	 * @throws MilkTeaException
	 */
	public TeaUserInfo userLogin(String telephone,String userPassword) throws MilkTeaException;
	
	
	/**
	 * 更改用户密码
	 * @param telephone
	 * @param userPassword
	 * @return
	 * @throws MilkTeaException
	 */
	public int modifyUserPassword(String telephone,String userPassword) throws MilkTeaException;
	
	
	/**
	 * 网页登入取得微信信息
	 * @param weixinOpenId
	 * @return
	 * @throws MilkTeaException
	 */
	public TeaLoginWeixin getWeixinInfor(String weixinOpenId) throws MilkTeaException;
	
   
   
}
