package com.milktea.milkteauser.vo;

/**
 * Cteated by cxy on 2018/10/30
 * 微信支付VO
 */
public class WXPayVo {
    //订单编号
    private String orderNO;
    //简单商品描述
    private String body;
    //用户id
    private String userId;

    public String getOrderNO() {
        return orderNO;
    }

    public void setOrderNO(String orderNO) {
        this.orderNO = orderNO;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
