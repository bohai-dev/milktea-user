package com.milktea.milkteauser.vo;

/**
 * Cteated by cxy on 2018/9/21
 */
public class IotResponseBean {
    private String payOrderId;
    private String mchId;
    private String mchOrderNo;
    private String channelId;
    private String currency;
    private int amount;
    private int status;
    private String clientIp;
    private String device;
    private String subject;
    private String body;
    private String channelOrderNo;
    private String param1;
    private String param2;
    private long paySuccTime;
    private int backType;
    private String sign;
    private String params;

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getChannelOrderNo() {
        return channelOrderNo;
    }

    public void setChannelOrderNo(String channelOrderNo) {
        this.channelOrderNo = channelOrderNo;
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

    public long getPaySuccTime() {
        return paySuccTime;
    }

    public void setPaySuccTime(long paySuccTime) {
        this.paySuccTime = paySuccTime;
    }

    public int getBackType() {
        return backType;
    }

    public void setBackType(int backType) {
        this.backType = backType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
    
    

    public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "IotResponseBean [payOrderId=" + payOrderId + ", mchId=" + mchId + ", mchOrderNo=" + mchOrderNo
				+ ", channelId=" + channelId + ", currency=" + currency + ", amount=" + amount + ", status=" + status
				+ ", clientIp=" + clientIp + ", device=" + device + ", subject=" + subject + ", body=" + body
				+ ", channelOrderNo=" + channelOrderNo + ", param1=" + param1 + ", param2=" + param2 + ", paySuccTime="
				+ paySuccTime + ", backType=" + backType + ", sign=" + sign + ", params=" + params + "]";
	}

	
}
