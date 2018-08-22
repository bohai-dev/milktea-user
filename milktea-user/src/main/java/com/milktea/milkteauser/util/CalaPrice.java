package com.milktea.milkteauser.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.milktea.milkteauser.dao.TeaAttributesInfoMapper;
import com.milktea.milkteauser.dao.TeaGoodsInfoMapper;
import com.milktea.milkteauser.domain.TeaAttributesInfo;
import com.milktea.milkteauser.domain.TeaGoodsInfo;
import com.milktea.milkteauser.domain.TeaOrderDetails;
import com.milktea.milkteauser.domain.TeaOrderDetailsAttr;
import com.milktea.milkteauser.vo.CustOrderInfoVo;
import com.milktea.milkteauser.vo.PromotionVo;
import com.milktea.milkteauser.vo.ResponseBody;
@Service("calaPrice")
public class CalaPrice {
	
	@Autowired
	TeaGoodsInfoMapper teaGoodsInfoMapper;
	@Autowired
	TeaAttributesInfoMapper teaAttributesInfoMapper;
	
	/**
	 * 商品的原始价格计算
	 * @param custOrderInfoVo
	 * @return 原始价格
	 */
	public CustOrderInfoVo balanceAccount(CustOrderInfoVo custOrderInfoVo){
		CustOrderInfoVo retCustOrderInfoVo = new CustOrderInfoVo();
		
		
		//取得下单LIST
		List<TeaOrderDetails> listTeaOrderDetails = custOrderInfoVo.getListTeaOrderDetails();
		
		BigDecimal bookTotalAccount = new BigDecimal(0);
		
		for (TeaOrderDetails teaOrderDetails : listTeaOrderDetails) {
			TeaGoodsInfo teaGoodsInfo = new TeaGoodsInfo();
			teaGoodsInfo = teaGoodsInfoMapper.selectByPrimaryKey(teaOrderDetails.getGoodsId());
			teaOrderDetails.setOrigPrice(teaGoodsInfo.getGoodsPrice());
			
			//取得下单配料LISt
			List<TeaOrderDetailsAttr> listTeaOrderDetailsAttr = new ArrayList<TeaOrderDetailsAttr>();
			listTeaOrderDetailsAttr = teaOrderDetails.getListTeaOrderDetailsAttr();
			
			BigDecimal attrBookTotalAccount = new BigDecimal(0);
			
			for (TeaOrderDetailsAttr teaOrderDetailsAttr : listTeaOrderDetailsAttr) {
				TeaAttributesInfo teaAttributesInfo = new TeaAttributesInfo();
				
				teaAttributesInfo = teaAttributesInfoMapper.selectByPrimaryKey(teaOrderDetailsAttr.getAttrId());
				attrBookTotalAccount = attrBookTotalAccount.add(teaAttributesInfo.getAttrPrice());
				
			}
			
			//配料价格再计算
			teaOrderDetails.setAttrPrice(attrBookTotalAccount);
			
			bookTotalAccount = bookTotalAccount.add(teaGoodsInfo.getGoodsPrice());
			bookTotalAccount = bookTotalAccount.add(attrBookTotalAccount);
		}
		
		
		custOrderInfoVo.setOrderPrice(bookTotalAccount);
		
		return custOrderInfoVo;
	}
	
	/**
	 * 根据优惠码计算优惠了多少钱返回
	 * @param custOrderInfoVo
	 * @param promotionId
	 * @return 优惠了多少钱
	 */
	public CustOrderInfoVo promotionBalanceAccount(CustOrderInfoVo custOrderInfoVo,String promotionId){
		BigDecimal retBigDec = new BigDecimal(0);
		if(promotionId.equals("")){
			//没有优惠码则返回0
			custOrderInfoVo.setDiscount(new BigDecimal(0));
			return custOrderInfoVo;
		}
		//根据店铺编号和活动ID查询有效活动详情
		String path = "http://localhost:8081/queryEffectPromotion"; 

		try {

			HttpUtil HttpUtil = new HttpUtil();
			Map<String,String> mapParam = new HashMap<String,String>();
			mapParam.put("storeNo", custOrderInfoVo.getStoreNo());
			mapParam.put("promotionId", custOrderInfoVo.getPromotionId());
			String retStr = HttpUtil.post(path, mapParam);
			System.out.println(retStr);
			ResponseBody<PromotionVo> retpromotionVo= JSON.parseObject(retStr, new TypeReference<ResponseBody<PromotionVo>>() {});
			
			if(null != retpromotionVo.getData()){
				//优惠活动成立
				// 默认为第二杯半价
				
				return calaPromotion(custOrderInfoVo);
				
			} else {
				custOrderInfoVo.setDiscount(new BigDecimal(0));
				return custOrderInfoVo;
			}

			
			
		
          
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
		
		
		return custOrderInfoVo;
	}
	
    private CustOrderInfoVo calaPromotion(CustOrderInfoVo custOrderInfoVo)
    {
    	//计算第二杯半价
    	List<TeaOrderDetails> listTeaOrderDetails = custOrderInfoVo.getListTeaOrderDetails();
    	
    	List<BigDecimal> listInt = new ArrayList<BigDecimal>();

    	//得到最大的优惠杯数
    	int maxCap = 0;
    	maxCap = listTeaOrderDetails.size() / 2 ;
    	
    	if(listTeaOrderDetails.size() < 2){
    		custOrderInfoVo.setDiscount(new BigDecimal(0));
    		return custOrderInfoVo;
    	}
    	
    	int i = 0;
    	for (TeaOrderDetails teaOrderDetails : listTeaOrderDetails) {
    		//一杯奶茶总价优惠
    		listInt.add(teaOrderDetails.getOrderPrice());
    		//仅奶茶优惠
//    		listInt.add(teaOrderDetails.getOrigPrice());
			
		}
    	
    	//排序
    	Collections.sort(listInt);
    	
    	BigDecimal promotionValue = listInt.get(maxCap);
    	
    	BigDecimal promotionTotalValue = new BigDecimal(0);
    	
    	
    	int countpromtion = 0;
    	for (TeaOrderDetails teaOrderDetails : listTeaOrderDetails) {
    		if(teaOrderDetails.getOrigPrice().compareTo(promotionValue) < 0){
    			//在优惠价格以下的商品
    			countpromtion = countpromtion + 1;
    			//一杯奶茶总价优惠
    			teaOrderDetails.setDiscount(teaOrderDetails.getOrderPrice().divide(new BigDecimal(2)));
    			promotionTotalValue = promotionTotalValue.add(teaOrderDetails.getDiscount());
    			teaOrderDetails.setOrderPrice(teaOrderDetails.getOrigPrice().subtract(teaOrderDetails.getDiscount()));
    			//仅奶茶优惠
//    			teaOrderDetails.setDiscount(teaOrderDetails.getOrigPrice().divide(new BigDecimal(2)));
//    			promotionTotalValue = promotionTotalValue.add(teaOrderDetails.getDiscount());
//    			teaOrderDetails.setOrderPrice(teaOrderDetails.getOrigPrice().subtract(teaOrderDetails.getDiscount()).add(teaOrderDetails.getAttrPrice()));
    		}
			
		}
    	
    	for (TeaOrderDetails teaOrderDetails : listTeaOrderDetails) {
    		if(teaOrderDetails.getOrigPrice().compareTo(promotionValue) == 0){
    			if(countpromtion < maxCap){
    				//等于优惠价格的商品
        			countpromtion = countpromtion + 1;
        			//一杯奶茶总价优惠
        			teaOrderDetails.setDiscount(teaOrderDetails.getOrderPrice().divide(new BigDecimal(2)));
        			promotionTotalValue = promotionTotalValue.add(teaOrderDetails.getDiscount());
        			teaOrderDetails.setOrderPrice(teaOrderDetails.getOrigPrice().subtract(teaOrderDetails.getDiscount()));
        			//仅奶茶优惠
//        			teaOrderDetails.setDiscount(teaOrderDetails.getOrigPrice().divide(new BigDecimal(2)));
//        			promotionTotalValue = promotionTotalValue.add(teaOrderDetails.getDiscount());
//        			teaOrderDetails.setOrderPrice(teaOrderDetails.getOrigPrice().subtract(teaOrderDetails.getDiscount()).add(teaOrderDetails.getAttrPrice()));
    			}
    			
    		}
			
		}
    	
    	//总订单的优惠及影响总价
    	custOrderInfoVo.setDiscount(promotionTotalValue);
    	custOrderInfoVo.setOrderPrice(custOrderInfoVo.getOrderPrice().subtract(promotionTotalValue));
    	
    	
    	
    	
    	
    	
    	return custOrderInfoVo;
    }

}
