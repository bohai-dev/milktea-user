package com.milktea.milkteauser.vo;

/**
 * Cteated by cxy on 2018/9/19
 */
public class IOTBean {
    private static  final  String NOTIFY_URL="https://www.primes-thaitea.com/iotpay/notify";
    private static final   String REMOTE_IP="47.89.247.54";
    private static final   String MCHID="10000882";
    //商户id
    private String mchId;
    //订单编号
    private String mchOrderNo;
    //渠道id
    private String channelId;
    //货币
    private String currency;
    //支付金额，单位分
    private int amount;
    //客户端ip
    private String clientIp;
    //设备
    private String device;
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
    //附加参数
    private String extra;
    //签名
    private String sign;

    public IOTBean() {
        this.mchId=MCHID;
        this.clientIp=REMOTE_IP;
        this.notifyUrl=NOTIFY_URL;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchOrderNo() {
        return mchOrderNo;
    }

    public void setMchOrderNo(String mchOrderNo) {
        this.mchOrderNo = mchOrderNo;
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

    public String getClientIp() {
        return clientIp;
    }
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
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
}
