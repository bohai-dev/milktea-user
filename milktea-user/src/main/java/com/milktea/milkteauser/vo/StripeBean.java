package com.milktea.milkteauser.vo;

public class StripeBean {
    //暂定四个参数，后面可以再添加
    //支付token，前台返回
    private String token;
    //支付数量，分
    private Integer amount;
    //商品描述
    private String  description;
    //订单编号
    private String orderNum;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}
