package com.milktea.milkteauser.vo;

import java.util.List;

import com.milktea.milkteauser.domain.TeaOrderDetails;
import com.milktea.milkteauser.domain.TeaOrderDetailsAttr;
import com.milktea.milkteauser.domain.TeaOrderInfo;



/**
 * 商品信息
 * @author caoxx
 *
 */
public class CustOrderInfoVo {
    
    //客户下单信息
	private TeaOrderInfo teaOrderInfo;
	
	//客户下单明细
	private List<TeaOrderDetails> listTeaOrderDetails;
	
	//客户辅料明细
	private List<TeaOrderDetailsAttr> TeaOrderDetailsAttr;
	
	
}
