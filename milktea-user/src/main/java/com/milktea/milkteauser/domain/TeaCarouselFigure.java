package com.milktea.milkteauser.domain;

import java.util.Date;

public class TeaCarouselFigure {
    private String figureId;

    private String storeNo;

    private String cnFigureAddress;

    private String usFigureAddress;

    private Short indexNo;

    private String remark;

    private String deleteFlag;

    private Date updateTime;

    public String getFigureId() {
        return figureId;
    }

    public void setFigureId(String figureId) {
        this.figureId = figureId == null ? null : figureId.trim();
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo == null ? null : storeNo.trim();
    }

    public String getCnFigureAddress() {
        return cnFigureAddress;
    }

    public void setCnFigureAddress(String cnFigureAddress) {
        this.cnFigureAddress = cnFigureAddress == null ? null : cnFigureAddress.trim();
    }

    public String getUsFigureAddress() {
        return usFigureAddress;
    }

    public void setUsFigureAddress(String usFigureAddress) {
        this.usFigureAddress = usFigureAddress == null ? null : usFigureAddress.trim();
    }

    public Short getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Short indexNo) {
        this.indexNo = indexNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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