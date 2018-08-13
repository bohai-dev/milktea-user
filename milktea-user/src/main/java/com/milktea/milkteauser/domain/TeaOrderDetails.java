package com.milktea.milkteauser.domain;

import java.math.BigDecimal;
import java.util.List;

public class TeaOrderDetails {
    private String orderDetailId;

    private String orderNo;

    private String goodsId;

    private BigDecimal origPrice;

    private BigDecimal discount;

    private BigDecimal orderPrice;

    private String updateTime;
    
    private List<TeaOrderDetailsAttr> listTeaOrderDetailsAttr;

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId == null ? null : orderDetailId.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

	public List<TeaOrderDetailsAttr> getListTeaOrderDetailsAttr() {
		return listTeaOrderDetailsAttr;
	}

	public void setListTeaOrderDetailsAttr(List<TeaOrderDetailsAttr> listTeaOrderDetailsAttr) {
		this.listTeaOrderDetailsAttr = listTeaOrderDetailsAttr;
	}
    
}