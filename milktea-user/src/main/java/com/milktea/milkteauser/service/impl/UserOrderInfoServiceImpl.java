package com.milktea.milkteauser.service.impl;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.milktea.milkteauser.controller.UserLoginController;
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
import com.milktea.milkteauser.util.HttpUtil;
import com.milktea.milkteauser.vo.CustOrderInfoVo;
import com.milktea.milkteauser.vo.ResponseBody;


@Service("userOrderInfoService")
public  class UserOrderInfoServiceImpl implements UserOrderInfoService {
	
	@Autowired
	TeaOrderInfoMapper teaOrderInfoMapper;
	@Autowired
	TeaOrderDetailsMapper teaOrderDetailsMapper;
	@Autowired
	TeaOrderDetailsAttrMapper teaOrderDetailsAttrMapper;
	@Autowired
	CalaPrice calaPrice;
	
	
	
	
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
		//CHECK 奶茶数量 是否能下单 因为没有冻结及待支付状态，只能直减，不支付的就不能自动返回
		int retCheckNum = checkNum(custOrderInfoVo);
		if(retCheckNum == 1){
			logger.error(MilkTeaErrorConstant.LAZY_WEIGHT.getCnErrorMsg());
	         throw new MilkTeaException(MilkTeaErrorConstant.LAZY_WEIGHT);
		}
		
		
		//得到新的订单编号 YYYYMMDD_A_000000
		// TODO:如果每一天都要连番从一开始则要重置SEQ
		String custOrderSeq = teaOrderInfoMapper.getCustOrderSeq();
		//订单编号
		CustOrderInfoTemp.setOrderNo(custOrderSeq);
		//用户编号 用户手机号 微信ID 活动ID 客户下单备注 下单时间  STORE_NO 由前端提供
		//原始价格计算 ORIG_PRICE
		
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
	          BeanUtils.copyProperties(CustOrderInfoTemp,dest);
	          this.teaOrderInfoMapper.insertSelective(dest);
	      } catch (Exception e) {
	          logger.error(MilkTeaErrorConstant.UNKNOW_EXCEPTION.getCnErrorMsg(), e);
	          throw new MilkTeaException(MilkTeaErrorConstant.UNKNOW_EXCEPTION, e);
	      }
	      
	    //TeaOrderDetails insert
	      List<TeaOrderDetails> listTeaOrderDetails = custOrderInfoVo.getListTeaOrderDetails();
	      for (TeaOrderDetails teaOrderDetails : listTeaOrderDetails) {
	    	  teaOrderDetails.setOrderNo(CustOrderInfoTemp.getOrderNo());
	    	// TODO:如果每一天都要连番从一开始则要重置SEQ
				String orderDetailIdSeq = teaOrderDetailsMapper.getOrderDetailsSeq();
				teaOrderDetails.setOrderDetailId(orderDetailIdSeq);
	    	  
	    	  teaOrderDetailsMapper.insertSelective(teaOrderDetails);
	    	  
	    	  List<TeaOrderDetailsAttr> listTeaOrderDetailsAttr = new ArrayList<TeaOrderDetailsAttr>();
			  listTeaOrderDetailsAttr = teaOrderDetails.getListTeaOrderDetailsAttr();
			
			  
			  for (TeaOrderDetailsAttr teaOrderDetailsAttr : listTeaOrderDetailsAttr) {
				  teaOrderDetailsAttr.setOrderDetailId(orderDetailIdSeq);
				  
				  teaOrderDetailsAttrMapper.insertSelective(teaOrderDetailsAttr);
				
			}
			  
			  
	    	  
		}
		
		return CustOrderInfoTemp;
	}

	@Override
	public Integer modifyOrderStatus(String orderNo,String orderStatus) throws MilkTeaException {
		//更新订单状态
		
		int result=teaOrderInfoMapper.modifyOrderStatus(orderNo, orderStatus);
		
		
		return result;
	}
	@Override
	public Integer updatePayStatus(String orderNo,String payStatus)throws MilkTeaException{
	    //更新订单的支付状态
		int result=teaOrderInfoMapper.updatePayStatus(orderNo, payStatus);
		return result;
	}

	@Override
	public Integer finishPayModfiyOrder(String orderNo, String remark, String orderTime) throws MilkTeaException {
		//更新订单状态
		String orderType = "";
		if("".equals(orderTime) || null == orderTime){
			//直接下单
			orderType = "1";
			return teaOrderInfoMapper.finishPayModfiyOrder2(orderNo, remark,orderType);
		} else {
			//预约单
			orderType = "0";
			return teaOrderInfoMapper.finishPayModfiyOrder1(orderNo, remark,orderTime,orderType);
		}

				
				
		
	}

	@Override
	public List<CustOrderInfoVo> findOrderByTelephone(String telephone, String flag) throws MilkTeaException {
		//FLAG 0:为全部  1为完成订单 2为未完成订单
		List<TeaOrderInfo> listTeaOrderInfo = new ArrayList<TeaOrderInfo>();
		listTeaOrderInfo = teaOrderInfoMapper.findOrderByTelephone(telephone,flag);
		
		return null;
	}

	@Override
	public TeaOrderInfo findOrderByOrderNo(String orderNo) throws MilkTeaException {
		TeaOrderInfo teaOrderInfo = new TeaOrderInfo();
		teaOrderInfo = teaOrderInfoMapper.selectByPrimaryKey(orderNo);
		return teaOrderInfo;
	}
	
	public int checkNum(CustOrderInfoVo custOrderInfoVo) throws MilkTeaException{
		//返回0 为正常 1为扣除数量失败
		//TODO
		List<TeaOrderDetails> listTeaOrderDetails = new ArrayList<TeaOrderDetails>();
		List<String> listGoodsId = new ArrayList<String>();
		int retflg = 0;
		
//		ListTeaOrderDetails ListTeaOrderDetails = new ListTeaOrderDetails();
		listTeaOrderDetails = custOrderInfoVo.getListTeaOrderDetails();
		for (TeaOrderDetails teaOrderDetails : listTeaOrderDetails) {
			//判断是否有库存 扣减商品库存
			//deductGoodsStock DeductGoodsStockRequestVo goodsId volume
			
			BufferedReader in = null;
			String result = "";
			Logger logger = LoggerFactory.getLogger(UserLoginController.class);
			ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
			JsonObject message = new JsonObject();
			PrintWriter out = null;
			String path = "http://localhost:8081/deductGoodsStock"; 
			try {

				HttpUtil HttpUtil = new HttpUtil();
				Map<String,String> mapParam = new HashMap<String,String>();
				mapParam.put("goodsId", teaOrderDetails.getGoodsId());
				mapParam.put("volume", "1");
				String retStr = HttpUtil.post(path, mapParam);
				System.out.println(retStr);
				JSONObject json = JSON.parseObject(result);
				String retCode = json.getString("rspCode");
				if(!"00000".equals(retCode)){
					// 库存或者其他错误 减一库存失败
					retflg = 1;
					break;
				}
		      
		        
	          
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			
			if(retflg == 0){
				//list保存当前已有的库存，用于回退，因为没有冻结
				listGoodsId.add(teaOrderDetails.getGoodsId());
			}
			
		}
		
		if(retflg == 1){
			//因为没有冻结,所以还回去
			
			for (String goodsId : listGoodsId) {
				BufferedReader in = null;
				String result = "";
				Logger logger = LoggerFactory.getLogger(UserLoginController.class);
				ResponseBody<JSONObject> responseBody = new ResponseBody<JSONObject>();
				JsonObject message = new JsonObject();
				PrintWriter out = null;
				String path = "http://localhost:8081/deductGoodsStock"; 
				try {

					HttpUtil HttpUtil = new HttpUtil();
					Map<String,String> mapParam = new HashMap<String,String>();
					mapParam.put("goodsId", goodsId);
					mapParam.put("volume", "-1");
					String retStr = HttpUtil.post(path, mapParam);
					System.out.println(retStr);
					JSONObject json = JSON.parseObject(result);
					String retCode = json.getString("rspCode");
		          
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
			return 1;
			
			
		}
		
		
		
		
		return 0;
	}
	
	
	
	



	
    
}
