package com.milktea.milkteauser.vo;

public class ResponseHeader {

    private String rspCode = "00000";
    
    private String cnErrorMsg;
    
    private String usErrorMsg;

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public String getCnErrorMsg() {
        return cnErrorMsg;
    }

    public void setCnErrorMsg(String cnErrorMsg) {
        this.cnErrorMsg = cnErrorMsg;
    }

    public String getUsErrorMsg() {
        return usErrorMsg;
    }

    public void setUsErrorMsg(String usErrorMsg) {
        this.usErrorMsg = usErrorMsg;
    }
}
