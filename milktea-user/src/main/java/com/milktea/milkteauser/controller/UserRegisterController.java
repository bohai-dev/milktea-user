package com.milktea.milkteauser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milktea.milkteauser.domain.TeaSmsRegister;
import com.milktea.milkteauser.domain.TeaUserInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserInfoService;
import com.milktea.milkteauser.service.UserRegisterService;
import com.milktea.milkteauser.vo.ResponseBody;
import com.milktea.milkteauser.vo.ResponseHeader;



/**
 * @author caoxx
 *
 */
@RestController
@RequestMapping("/userRegister")
public class UserRegisterController {

	@Autowired
	UserInfoService userInfoService;
	@Autowired
	UserRegisterService userRegisterService;
	
	
	/**
	 * @param teaUserInfo
	 * @return 
	 * @throws MilkTeaException
	 * 新用户插入
	 */
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ResponseHeader  insert(@RequestBody TeaUserInfo teaUserInfo) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userInfoService.insert(teaUserInfo);
		return header;
	}
	
	/**
	 * 客户登入验证
	 * @param telephone
	 * @param userPassword
	 * @return
	 * @throws MilkTeaException
	 */
	@RequestMapping(value="/userLogin", method = RequestMethod.GET)
	public ResponseBody<TeaUserInfo>  userLogin(@RequestParam("telephone") String telephone,@RequestParam("userPassword") String userPassword) throws MilkTeaException{
		ResponseBody<TeaUserInfo> responseBody = new ResponseBody<>();
		responseBody.setData(this.userRegisterService.userLogin(telephone,userPassword));
		return responseBody;
	}
	
	
	/**
	 * 根据手机号改修密码
	 * @param telephone
	 * @param userPassword
	 * @return
	 * @throws MilkTeaException
	 */
	@RequestMapping(value="/modifyUserPassword", method = RequestMethod.GET)
	public ResponseHeader  modifyUserPassword(@RequestParam("telephone") String telephone,@RequestParam("userPassword") String userPassword) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userRegisterService.modifyUserPassword(telephone,userPassword);
		return header;
	}
	
	
	/**
	 * 微信OPENID查找，返回客户微信信息，如果为NULL返回TeaUserInfo
	 * @param teaUserInfo
	 * @return TeaUserInfo
	 * @throws MilkTeaException
	 */
	@RequestMapping(value="/findUserByWeixinOpenid", method = RequestMethod.GET)
	public ResponseBody<TeaUserInfo> findUserByWeixinOpenid(@RequestParam("weixinOpenid") String weixinOpenid) throws MilkTeaException{
		ResponseBody<TeaUserInfo> responseBody = new ResponseBody<>();
		responseBody.setData(this.userRegisterService.findRegisterOpenid(weixinOpenid));
		return responseBody;
	}
	
	/**
	 * 根据手机号查找客户信息，如果为NULL返回TeaUserInfo
	 * @param teaUserInfo
	 * @return TeaUserInfo
	 * @throws MilkTeaException
	 */
	@RequestMapping(value="/findUserByTelephone", method = RequestMethod.GET)
	public ResponseBody<TeaUserInfo> findUserByTelephone(@RequestParam("telephone") String telephone) throws MilkTeaException{
		ResponseBody<TeaUserInfo> responseBody = new ResponseBody<>();
		responseBody.setData(this.userRegisterService.findRegisterTelephone(telephone));
		return responseBody;
	}
	
	
	
	/**
	 * @param teaUserInfo
	 * @return ResponseHeader
	 * @throws MilkTeaException
	 * 当手机比微信先注册时 绑定微信号和手机号
	 */
	@RequestMapping(value="/bindOpenidTel", method = RequestMethod.POST)
	public ResponseHeader bindOpenidTel(@RequestBody TeaUserInfo teaUserInfo) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userRegisterService.bindOpenidTel(teaUserInfo);
		return header;
	}
	
	/**
	 * 认证号码生成
	 * @param teaUserInfo
	 * @return
	 * @throws MilkTeaException
	 */
	@RequestMapping(value="/createPollCode", method = RequestMethod.GET)
	public ResponseHeader createPollCode(@RequestParam("telephone") String telephone) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();
		this.userRegisterService.createPollCode(telephone);
		return header;
	}
	
	/**
	 * 认证号码认证（生产时设置60分钟过期，测试时2分钟过期）
	 * @param teaUserInfo
	 * @return 1为成功，0为失败，99为验证码错误，98为时间过期 
	 * @throws MilkTeaException
	 * 
	 */
	@RequestMapping(value="/comparePollCode", method = RequestMethod.POST)
	public ResponseHeader comparePollCode(@RequestBody TeaSmsRegister teaSmsRegister) throws MilkTeaException{
		ResponseHeader header = new ResponseHeader();

		this.userRegisterService.comparePollCode(teaSmsRegister);

		return header;
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
