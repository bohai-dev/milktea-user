package com.milktea.milkteauser.service;

import java.math.BigDecimal;
import java.util.List;

import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;




public interface UserInfoService {
	
	/**
     * 添加用户信息
     * @param figureVo
     * @throws MilkTeaException
     */
   public Integer insert(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
   
   
   /**
    * 根据ID查用户信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public TeaUserInfo selectByUserId(String userNo) throws MilkTeaException;
   
   /**
    * 修改用户信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public Integer  update(TeaUserInfo teaUserInfo) throws MilkTeaException;
   
   /**
    * 查询所有用户信息
    * @param figureVo
    * @throws MilkTeaException
    */
   public List<TeaUserInfo> selectAll() throws MilkTeaException;
   
   
   /**
    * 根据用户号 改变积分
 * @param USER_NO
 * @param point
 * @return
 * @throws MilkTeaException
 */
public int modifyPoint(String USER_NO,BigDecimal point) throws MilkTeaException;
   
   
}
