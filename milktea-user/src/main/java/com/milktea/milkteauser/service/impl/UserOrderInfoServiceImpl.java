package com.milktea.milkteauser.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.milktea.milkteauser.dao.TeaOrderDetailsAttrMapper;
import com.milktea.milkteauser.dao.TeaOrderDetailsMapper;
import com.milktea.milkteauser.dao.TeaOrderInfoMapper;
import com.milktea.milkteauser.domain.TeaOrderDetails;
import com.milktea.milkteauser.domain.TeaOrderDetailsAttr;
import com.milktea.milkteauser.domain.TeaOrderInfo;
import com.milktea.milkteauser.exception.MilkTeaErrorConstant;
import com.milktea.milkteauser.exception.MilkTeaException;
import com.milktea.milkteauser.service.UserOrderInfoService;
import com.milktea.milkteauser.util.CalaPrice;
import com.milktea.milkteauser.vo.CustOrderInfoVo;


@Service("userOrderInfoService")
public  class UserOrderInfoServiceImpl implements UserOrderInfoService {
	
	@Autowired
	TeaOrderInfoMapper teaOrderInfoMapper;
	@Autowired
	TeaOrderDetailsMapper teaOrderDetailsMapper;
	@Autowired
	TeaOrderDetailsAttrMapper teaOrderDetailsAttrMapper;
	
	
	
	static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Override
	public CustOrderInfoVo userOrderOper(CustOrderInfoVo custOrderInfoVo) throws MilkTeaException {
		TeaOrderInfo retTeaOrderInfo = new TeaOrderInfo();
		//客户订单解析
		CustOrderInfoVo CustOrderInfoTemp = new CustOrderInfoVo();
		CustOrderInfoTemp = custOrderInfoVo;
		
		
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
		CustOrderInfoTemp.setOrderNo(custOrderSeq);
		//用户编号 用户手机号 微信ID 活动ID 客户下单备注 下单时间  STORE_NO 由前端提供
		//原始价格计算 ORIG_PRICE
		CalaPrice calaPrice = new CalaPrice();
		BigDecimal origPrice = new BigDecimal(0);
		BigDecimal discount = new BigDecimal(0);
		CustOrderInfoTemp = calaPrice.balanceAccount(custOrderInfoVo);
		origPrice = CustOrderInfoTemp.getOrderPrice();
		CustOrderInfoTemp.setOrigPrice(origPrice);
		CustOrderInfoTemp.setOrderTime(new Date());
		
		
		//优惠价格 DISCOUNT 看参与的PROMOTION_ID 活动ID的详细信息
		//TODO:传入 STORE_NO和 PROMOTION_ID 返回优惠活动是否有效
		
		String promotionId = "";
		//TODO: 取得活动优惠内容
		promotionId = custOrderInfoVo.getPromotionId();
		CustOrderInfoTemp = calaPrice.promotionBalanceAccount(CustOrderInfoTemp,promotionId);
		
		//订单状态 0:已下单 1：制作完成 2:取货完成 3:外送 4:撤销
		CustOrderInfoTemp.setOrderStatus("0");
		
		//支付状态 0:待支付 1:支付成功 2:支付失败
		CustOrderInfoTemp.setPayStatus("0");
		
		//订单类型 0:预约 1:堂吃
		CustOrderInfoTemp.setOrderType("1");
		
		//删除标志 0：正常 1：删除
		CustOrderInfoTemp.setDeleteFlag("0");
		
		//其余参数等其他状态改变时再设置
		
		
		//订单插入数据库
		TeaOrderInfo dest = new TeaOrderInfo();
	      try {
	          BeanUtils.copyProperties(dest, CustOrderInfoTemp);
	          this.teaOrderInfoMapper.insert(dest);
	      } catch (Exception e) {
	          logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
	          throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
	      }
	      
	    //TeaOrderDetails insert
	      List<TeaOrderDetails> listTeaOrderDetails = custOrderInfoVo.getListTeaOrderDetails();
	      for (TeaOrderDetails teaOrderDetails : listTeaOrderDetails) {
	    	  teaOrderDetails.setOrderNo(CustOrderInfoTemp.getOrderNo());
	    	  teaOrderDetailsMapper.insert(teaOrderDetails);
	    	  
	    	  List<TeaOrderDetailsAttr> listTeaOrderDetailsAttr = new ArrayList<TeaOrderDetailsAttr>();
			  listTeaOrderDetailsAttr = teaOrderDetails.getListTeaOrderDetailsAttr();
			// TODO:如果每一天都要连番从一开始则要重置SEQ
				String orderDetailIdSeq = teaOrderDetailsMapper.getOrderDetailsSeq();
				teaOrderDetails.setOrderDetailId(orderDetailIdSeq);
			  
			  for (TeaOrderDetailsAttr teaOrderDetailsAttr : listTeaOrderDetailsAttr) {
				  teaOrderDetailsAttr.setOrderDetailId(orderDetailIdSeq);
				  
				  teaOrderDetailsAttrMapper.insert(teaOrderDetailsAttr);
				
			}
			  
			  
	    	  
		}
		
		return CustOrderInfoTemp;
	}

	@Override
	public Integer modifyOrderStatus(String orderNo,String orderStatus) throws MilkTeaException {
		//更新订单状态
		
		String result=teaOrderInfoMapper.modifyOrderStatus(orderNo, orderStatus);
		
		
		return 1;
	}



	
    
}
