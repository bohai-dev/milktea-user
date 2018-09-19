package com.milktea.milkteauser.vo;

/**
 * Cteated by cxy on 2018/9/19
 */
public class IOTBean {
    private static  final  String NOTIFY_URL="https://www.primes-thaitea.com/iotpay/notify";
    //订单编号
    private String orderNum;
    //渠道id
    private String channelId;
    //货币
    private String currency;
    //支付金额，单位分
    private int amount;
    //客户端ip
    private String clientIp;
    //设备
    private int device;
    //支付结果回调URL
    private String notifyUrl;

    //商品主题
    private String subject;

    //商品描述
    private String body;
    //扩展参数1
    private String param1;
    //扩展参数2
    private String param2;


    //附加参数 特定渠道发起时额外参数
    private String extra;

    //签名
    private String sign;


    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = NOTIFY_URL;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }
}
