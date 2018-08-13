package com.milktea.milkteauser.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.milktea.milkteauser.domain.TeaOrderDetails;



/**
 * 商品信息
 * @author caoxx
 *
 */
public class CustOrderInfoVo {
    
    //客户下单信息
	/*private TeaOrderInfo teaOrderInfo;
	
	public TeaOrderInfo getTeaOrderInfo() {
		return teaOrderInfo;
	}

	public void setTeaOrderInfo(TeaOrderInfo teaOrderInfo) {
		this.teaOrderInfo = teaOrderInfo;
	}

	public List<TeaOrderDetails> getListTeaOrderDetails() {
		return listTeaOrderDetails;
	}

	public void setListTeaOrderDetails(List<TeaOrderDetails> listTeaOrderDetails) {
		this.listTeaOrderDetails = listTeaOrderDetails;
	}

	

	//客户下单明细
	private List<TeaOrderDetails> listTeaOrderDetails;
	
	//客户辅料明细
	private List<TeaOrderDetailsAttr> listTeaOrderDetailsAttr;

	public List<TeaOrderDetailsAttr> getListTeaOrderDetailsAttr() {
		return listTeaOrderDetailsAttr;
	}

	public void setListTeaOrderDetailsAttr(List<TeaOrderDetailsAttr> listTeaOrderDetailsAttr) {
		this.listTeaOrderDetailsAttr = listTeaOrderDetailsAttr;
	}*/
	
	private String orderNo;

    private String userNo;

    private String telephone;

    private String weixinId;

    private String promotionId;

    private String remark;

    private BigDecimal origPrice;

    private BigDecimal discount;

    private BigDecimal orderPrice;

    private String takeNo;

    private Date orderTime;

    private String orderStatus;

    private String payStatus;

    private String orderType;

    private Date makeFinishTime;

    private Date bookTime;

    private String deleteFlag;

    private Date updateTime;
    
    private List<TeaOrderDetails> listTeaOrderDetails;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWeixinId() {
		return weixinId;
	}

	public void setWeixinId(String weixinId) {
		this.weixinId = weixinId;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(BigDecimal origPrice) {
		this.origPrice = origPrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getTakeNo() {
		return takeNo;
	}

	public void setTakeNo(String takeNo) {
		this.takeNo = takeNo;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getMakeFinishTime() {
		return makeFinishTime;
	}

	public void setMakeFinishTime(Date makeFinishTime) {
		this.makeFinishTime = makeFinishTime;
	}

	public Date getBookTime() {
		return bookTime;
	}

	public void setBookTime(Date bookTime) {
		this.bookTime = bookTime;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<TeaOrderDetails> getListTeaOrderDetails() {
		return listTeaOrderDetails;
	}

	public void setListTeaOrderDetails(List<TeaOrderDetails> listTeaOrderDetails) {
		this.listTeaOrderDetails = listTeaOrderDetails;
	}
    
    
	
}
