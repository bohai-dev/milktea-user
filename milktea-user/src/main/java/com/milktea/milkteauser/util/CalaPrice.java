package com.milktea.milkteauser.util;

import java.math.BigDecimal;

import com.milktea.milkteauser.vo.CustOrderInfoVo;

public class CalaPrice {
	
	/**
	 * 商品的原始价格计算
	 * @param custOrderInfoVo
	 * @return 原始价格
	 */
	public BigDecimal balanceAccount(CustOrderInfoVo custOrderInfoVo){
		BigDecimal retBigDec = new BigDecimal(0);
		
		
		
		return retBigDec;
	}
	
	/**
	 * 根据优惠码计算优惠了多少钱返回
	 * @param custOrderInfoVo
	 * @param promotionId
	 * @return 优惠了多少钱
	 */
	public BigDecimal promotionBalanceAccount(CustOrderInfoVo custOrderInfoVo,String promotionId){
		BigDecimal retBigDec = new BigDecimal(0);
		if(promotionId.equals("")){
			//没有优惠码则返回0
			return retBigDec;
		}
		
		return retBigDec;
	}

}
