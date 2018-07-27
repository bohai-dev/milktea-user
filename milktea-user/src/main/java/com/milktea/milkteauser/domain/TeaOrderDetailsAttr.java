package com.milktea.milkteauser.domain;

public class TeaOrderDetailsAttr {
    private String orderDetailId;

    private String attrId;

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId == null ? null : orderDetailId.trim();
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId == null ? null : attrId.trim();
    }
}