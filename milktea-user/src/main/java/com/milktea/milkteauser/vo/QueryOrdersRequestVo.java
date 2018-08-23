package com.milktea.milkteauser.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QueryOrdersRequestVo {
    
    private String userNo;
    
    private String telephone;
    
    private String orderNo;
    
    private String lang;
    
    private String storeNo;
    
    private String promotionId;
    
    //订单类型 0:预约 1:堂吃
    private String orderType;
    
    //订单状态 0:已下单 1：制作完成 2:取货完成 3:外送 4:撤销
    private String orderStatus;
    
    //支付状态 0:待支付 1:支付成功 2:支付失败
    private String payStatus;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date beginDate;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endDate;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
    
}
