package com.milktea.milkteauser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseBody<CustOrderInfoVo>  userOrderOper(@RequestBody CustOrderInfoVo custOrderInfoVo) throws MilkTeaException{
		ResponseBody responseBody = new ResponseBody();
		CustOrderInfoVo CustOrderInfoVo = this.userOrderInfoService.userOrderOper(custOrderInfoVo);
		responseBody.setData(CustOrderInfoVo);
		return responseBody;
	}
	
	@RequestMapping(value="/findOrderByTelephone", method = RequestMethod.GET)
	public ResponseBody<List<CustOrderInfoVo>>  findOrderByTelephone(@RequestParam("telephone") String telephone,@RequestParam("flag") String flag) throws MilkTeaException{
		ResponseBody responseBody = new ResponseBody();
		List<CustOrderInfoVo> listCustOrderInfoVo = this.userOrderInfoService.findOrderByTelephone(telephone,flag);
		responseBody.setData(listCustOrderInfoVo);
		return responseBody;
	}
	
	
	
	/**
	 * 改变订单状态
	 * @param orderNo
	 * @param orderStatus
	 * @return
	 * @throws MilkTeaException
	 */
	@RequestMapping(value="/modfiyOrderStatus", method = RequestMethod.GET)
	public ResponseHeader  modfiyOrderStatus(@RequestParam("orderNo") String orderNo,@RequestParam("orderStatus") String orderStatus) throws MilkTeaException{
		ResponseHeader responseHeader = new ResponseHeader();
		this.userOrderInfoService.modifyOrderStatus(orderNo, orderStatus);
		return responseHeader;
	}
	
	
	/**
	 * 订单结算单生成后，再次改变 备注及订单类型属性
	 * @param orderNo
	 * @param orderStatus
	 * @return
	 * @throws MilkTeaException
	 */
	@RequestMapping(value="/finishPayModfiyOrder", method = RequestMethod.GET)
	public ResponseHeader  finishPayModfiyOrder(@RequestParam("orderNo") String orderNo,@RequestParam("remark") String remark,@RequestParam("orderTime") String orderTime) throws MilkTeaException{
		ResponseHeader responseHeader = new ResponseHeader();
		this.userOrderInfoService.finishPayModfiyOrder(orderNo, remark,orderTime);
		return responseHeader;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
