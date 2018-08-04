package com.milktea.milkteauser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@RequestMapping(value="/userOrderOper", method = RequestMethod.POST)
	public ResponseHeader  userOrderOper(CustOrderInfoVo custOrderInfoVo) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userOrderInfoService.userOrderOper(custOrderInfoVo);
		return header;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
