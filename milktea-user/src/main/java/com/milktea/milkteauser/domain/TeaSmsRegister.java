package com.milktea.milkteauser.domain;

import java.util.Date;

public class TeaSmsRegister {
    private String telephone;

    private String identifyCode;

    private Date identifyTime;

    private Short identifyFlag;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode == null ? null : identifyCode.trim();
    }

    public Date getIdentifyTime() {
        return identifyTime;
    }

    public void setIdentifyTime(Date identifyTime) {
        this.identifyTime = identifyTime;
    }

    public Short getIdentifyFlag() {
        return identifyFlag;
    }

    public void setIdentifyFlag(Short identifyFlag) {
        this.identifyFlag = identifyFlag;
    }
}