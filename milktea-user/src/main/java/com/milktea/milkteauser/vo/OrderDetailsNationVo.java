package com.milktea.milkteauser.vo;

import java.math.BigDecimal;
import java.util.List;

public class OrderDetailsNationVo {
    
    private String orderDetailId;

    private String orderNo;

    private String goodsId;

    private BigDecimal origPrice;

    private BigDecimal attrPrice;

    private BigDecimal discount;

    private BigDecimal orderPrice;
    
    private String goodsName;
    
    private String goodsPictureBig;
    
    private List<TeaAttributesInfoNationVo> attrs;

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getOrigPrice() {
        return origPrice;
    }

    public void setOrigPrice(BigDecimal origPrice) {
        this.origPrice = origPrice;
    }

    public BigDecimal getAttrPrice() {
        return attrPrice;
    }

    public void setAttrPrice(BigDecimal attrPrice) {
        this.attrPrice = attrPrice;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPictureBig() {
        return goodsPictureBig;
    }

    public void setGoodsPictureBig(String goodsPictureBig) {
        this.goodsPictureBig = goodsPictureBig;
    }

    public List<TeaAttributesInfoNationVo> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<TeaAttributesInfoNationVo> attrs) {
        this.attrs = attrs;
    }
    
    
}
