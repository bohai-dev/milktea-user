package com.milktea.milkteauser.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteashop.domain.TeaGoodsInfo;
import com.milktea.milkteashop.exception.MilkTeaErrorConstant;
import com.milktea.milkteauser.dao.TeaOrderInfoMapper;
import com.milktea.milkteauser.domain.TeaOrderDetails;
import com.milktea.milkteauser.domain.TeaOrderDetailsAttr;
import com.milktea.milkteauser.domain.TeaOrderInfo;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserOrderInfoService;
import com.milktea.milkteauser.vo.CustOrderInfoVo;


@Service("userOrderInfoService")
public  class UserOrderInfoServiceImpl implements UserOrderInfoService {
	
	@Autowired
	TeaOrderInfoMapper teaOrderInfoMapper;
	
	static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Override
	public TeaOrderInfo userOrderOper(CustOrderInfoVo custOrderInfoVo) throws MilkTeaException {
		TeaOrderInfo retTeaOrderInfo = new TeaOrderInfo();
		//客户订单解析
		CustOrderInfoVo CustOrderInfoTemp = new CustOrderInfoVo();
		CustOrderInfoTemp = custOrderInfoVo;
		
		//客户基础信息
		TeaOrderInfo teaOrderInfo = new TeaOrderInfo();
		teaOrderInfo = CustOrderInfoTemp.getTeaOrderInfo();
		
		//客户订单信息
		List<TeaOrderDetails> listTeaOrderDetails = new ArrayList<TeaOrderDetails>();
		listTeaOrderDetails = CustOrderInfoTemp.getListTeaOrderDetails();
		
		//客户辅料附加信息
		List<TeaOrderDetailsAttr> listTeaOrderDetailsAttr = new ArrayList<TeaOrderDetailsAttr>();
		listTeaOrderDetailsAttr = CustOrderInfoTemp.getListTeaOrderDetailsAttr();
		
//		TeaGoodsInfo dest = new TeaGoodsInfo();
//        try {
//            BeanUtils.copyProperties(dest, infoVo);
//            this.goodsInfoMapper.insert(dest);
//        } catch (Exception e) {
//            logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
//            throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
//        }
		
		//得到新的订单编号 YYYYMMDD_A_000000
		// TODO:如果每一天都要连番从一开始则要重置SEQ
		String custOrderSeq = teaOrderInfoMapper.getCustOrderSeq();
		//订单编号
		teaOrderInfo.setOrderNo(custOrderSeq);
		//用户编号 用户手机号 微信ID 活动ID 客户下单备注 下单时间  STORE_NO 由前端提供
		//原始价格计算 ORIG_PRICE
		//TODO:原始价格计算 调用子方法
		
		//优惠价格 DISCOUNT 看参与的PROMOTION_ID 活动ID的详细信息
		//TODO: 取得活动优惠内容
		
		//订单状态 0:已下单 1：制作完成 2:取货完成 3:外送 4:撤销
		teaOrderInfo.setOrderStatus("0");
		
		//支付状态 0:待支付 1:支付成功 2:支付失败
		teaOrderInfo.setPayStatus("0");
		
		//订单类型 0:预约 1:堂吃
		teaOrderInfo.setOrderType("1");
		
		//删除标志 0：正常 1：删除
		teaOrderInfo.setDeleteFlag("0");
		
		//其余参数等其他状态改变时再设置
		
		return retTeaOrderInfo;
	}



	
    
}
