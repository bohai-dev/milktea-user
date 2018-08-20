package com.milktea.milkteauser.controller;

import java.util.List;

import com.milktea.milkteauser.domain.TeaAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserInfoService;
import com.milktea.milkteauser.vo.ResponseBody;
import com.milktea.milkteauser.vo.ResponseHeader;



@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ResponseHeader  insert(TeaUserInfo teaUserInfo) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.insert(teaUserInfo);
		return header;
	}
	
	
	@RequestMapping(value="/selectAll", method = RequestMethod.POST)
	public ResponseBody<List<TeaUserInfo>> selectAll() throws MilkTeaException{
		ResponseBody<List<TeaUserInfo>> responseBody = new ResponseBody<>();
		responseBody.setData(this.userInfoService.selectAll());
		return responseBody;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ResponseHeader update(TeaUserInfo teaUserInfo) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.update(teaUserInfo);
		return header;
	}
	
	@RequestMapping(value="/selectByUserId", method = RequestMethod.POST)
	public ResponseBody<TeaUserInfo> selectByUserId(String userNo) throws MilkTeaException{
		ResponseBody<TeaUserInfo> responseBody = new ResponseBody<>();
		responseBody.setData(this.userInfoService.selectByUserId(userNo));
		return responseBody;
	}

	@RequestMapping("/getauser")
	public TeaAdmin  findOne(){
		TeaAdmin teaAdmin=new TeaAdmin();
		teaAdmin.setUserName("cxy");
		teaAdmin.setPasswd("123456");
		return  teaAdmin;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
