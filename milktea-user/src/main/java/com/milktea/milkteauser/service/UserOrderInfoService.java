package com.milktea.milkteauser.service;

import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.vo.CustOrderInfoVo;




public interface UserOrderInfoService {
	
	
 /**
 * @param CustOrderInfoVo
 * @return
 * @throws MilkTeaException
 */
public Integer userOrderOper(CustOrderInfoVo custOrderInfoVo) throws MilkTeaException;
   
  
   
   
}
