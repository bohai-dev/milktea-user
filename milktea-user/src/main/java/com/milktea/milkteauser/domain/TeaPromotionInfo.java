package com.milktea.milkteauser.domain;

import java.util.Date;

public class TeaPromotionInfo {
    private String promotionId;

    private String cnPromotionName;

    private String cnPromotionIntroduction;

    private String usPromotionName;

    private String usPromotionIntroduction;

    private Date beginTime;

    private Date endTime;

    private String promotionStatus;

    private String storeNos;

    private String deleteFlag;

    private Date updateTime;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId == null ? null : promotionId.trim();
    }

    public String getCnPromotionName() {
        return cnPromotionName;
    }

    public void setCnPromotionName(String cnPromotionName) {
        this.cnPromotionName = cnPromotionName == null ? null : cnPromotionName.trim();
    }

    public String getCnPromotionIntroduction() {
        return cnPromotionIntroduction;
    }

    public void setCnPromotionIntroduction(String cnPromotionIntroduction) {
        this.cnPromotionIntroduction = cnPromotionIntroduction == null ? null : cnPromotionIntroduction.trim();
    }

    public String getUsPromotionName() {
        return usPromotionName;
    }

    public void setUsPromotionName(String usPromotionName) {
        this.usPromotionName = usPromotionName == null ? null : usPromotionName.trim();
    }

    public String getUsPromotionIntroduction() {
        return usPromotionIntroduction;
    }

    public void setUsPromotionIntroduction(String usPromotionIntroduction) {
        this.usPromotionIntroduction = usPromotionIntroduction == null ? null : usPromotionIntroduction.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(String promotionStatus) {
        this.promotionStatus = promotionStatus == null ? null : promotionStatus.trim();
    }

    public String getStoreNos() {
        return storeNos;
    }

    public void setStoreNos(String storeNos) {
        this.storeNos = storeNos == null ? null : storeNos.trim();
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