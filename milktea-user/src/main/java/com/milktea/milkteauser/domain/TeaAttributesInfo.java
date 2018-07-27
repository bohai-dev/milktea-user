package com.milktea.milkteauser.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TeaAttributesInfo {
    private String attrId;

    private String cnAttrName;

    private String usAttrName;

    private String attrType;

    private BigDecimal attrPrice;

    private String deleteFlag;

    private Date updateTime;

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId == null ? null : attrId.trim();
    }

    public String getCnAttrName() {
        return cnAttrName;
    }

    public void setCnAttrName(String cnAttrName) {
        this.cnAttrName = cnAttrName == null ? null : cnAttrName.trim();
    }

    public String getUsAttrName() {
        return usAttrName;
    }

    public void setUsAttrName(String usAttrName) {
        this.usAttrName = usAttrName == null ? null : usAttrName.trim();
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType == null ? null : attrType.trim();
    }

    public BigDecimal getAttrPrice() {
        return attrPrice;
    }

    public void setAttrPrice(BigDecimal attrPrice) {
        this.attrPrice = attrPrice;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}