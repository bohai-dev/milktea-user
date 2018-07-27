package com.milktea.milkteauser.domain;

import java.util.Date;

public class TeaClassInfo {
    private String classId;

    private String cnClassName;

    private String cnClassLogo;

    private String usClassName;

    private String usClassLogo;

    private String classType;

    private Short indexNo;

    private String deleteFlag;

    private Date updateTime;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId == null ? null : classId.trim();
    }

    public String getCnClassName() {
        return cnClassName;
    }

    public void setCnClassName(String cnClassName) {
        this.cnClassName = cnClassName == null ? null : cnClassName.trim();
    }

    public String getCnClassLogo() {
        return cnClassLogo;
    }

    public void setCnClassLogo(String cnClassLogo) {
        this.cnClassLogo = cnClassLogo == null ? null : cnClassLogo.trim();
    }

    public String getUsClassName() {
        return usClassName;
    }

    public void setUsClassName(String usClassName) {
        this.usClassName = usClassName == null ? null : usClassName.trim();
    }

    public String getUsClassLogo() {
        return usClassLogo;
    }

    public void setUsClassLogo(String usClassLogo) {
        this.usClassLogo = usClassLogo == null ? null : usClassLogo.trim();
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType == null ? null : classType.trim();
    }

    public Short getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(Short indexNo) {
        this.indexNo = indexNo;
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