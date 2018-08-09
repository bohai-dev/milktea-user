package com.milktea.milkteauser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteauser.domain.TeaOrderInfo;
import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserOrderInfoService;
import com.milktea.milkteauser.vo.CustOrderInfoVo;
import com.milktea.milkteauser.vo.ResponseBody;
import com.milktea.milkteauser.vo.ResponseHeader;



@RestController
@RequestMapping("/userOrderInfo")
public class UserOrderInfoController {

	@Autowired
	UserOrderInfoService userOrderInfoService;
	
	/**
	 * @param custOrderInfoVo
	 * @return ResponseBody<TeaOrderInfo>
	 * @throws MilkTeaException
	 * 只有直接下单才进入此方法，
	 */
	@RequestMapping(value="/userOrderOper", method = RequestMethod.POST)
	public ResponseBody<TeaOrderInfo>  userOrderOper(@RequestBody CustOrderInfoVo custOrderInfoVo) throws MilkTeaException{
		ResponseBody responseBody = new ResponseBody();
		TeaOrderInfo teaOrderInfo = this.userOrderInfoService.userOrderOper(custOrderInfoVo);
		responseBody.setData(teaOrderInfo);
		return responseBody;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
