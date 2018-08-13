package com.milktea.milkteauser.service;

import com.milktea.milkteauser.domain.TeaOrderInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.vo.CustOrderInfoVo;




public interface UserOrderInfoService {
	
	
 /**
  * 客户下单操作
 * @param CustOrderInfoVo
 * @return CustOrderInfoVo
 * @throws MilkTeaException
 */
public CustOrderInfoVo userOrderOper(CustOrderInfoVo custOrderInfoVo) throws MilkTeaException;

/**
 * 更新订单状态
* @param orderStatus
* @return Integer
* @throws MilkTeaException
*/
public Integer modifyOrderStatus(String orderNo,String orderStatus) throws MilkTeaException;
   
  
   
   
}
