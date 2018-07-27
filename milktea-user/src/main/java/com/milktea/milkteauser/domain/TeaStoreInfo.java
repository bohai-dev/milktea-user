package com.milktea.milkteauser.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TeaStoreInfo {
    private String storeNo;

    private String cnStoreName;

    private String cnStoreCity;

    private String cnStoreAddress;

    private String cnStorePicture;

    private String cnStoreIntroduction;

    private String usStoreName;

    private String usStoreCity;

    private String usStoreAddress;

    private String usStorePicture;

    private String usStoreIntroduction;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String storePhone;

    private String storeUserName;

    private String storePasswd;

    private String isSend;

    private String reserveBeginTime;

    private String reserveEndTime;

    private String deleteFlag;

    private Date updateTime;

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo == null ? null : storeNo.trim();
    }

    public String getCnStoreName() {
        return cnStoreName;
    }

    public void setCnStoreName(String cnStoreName) {
        this.cnStoreName = cnStoreName == null ? null : cnStoreName.trim();
    }

    public String getCnStoreCity() {
        return cnStoreCity;
    }

    public void setCnStoreCity(String cnStoreCity) {
        this.cnStoreCity = cnStoreCity == null ? null : cnStoreCity.trim();
    }

    public String getCnStoreAddress() {
        return cnStoreAddress;
    }

    public void setCnStoreAddress(String cnStoreAddress) {
        this.cnStoreAddress = cnStoreAddress == null ? null : cnStoreAddress.trim();
    }

    public String getCnStorePicture() {
        return cnStorePicture;
    }

    public void setCnStorePicture(String cnStorePicture) {
        this.cnStorePicture = cnStorePicture == null ? null : cnStorePicture.trim();
    }

    public String getCnStoreIntroduction() {
        return cnStoreIntroduction;
    }

    public void setCnStoreIntroduction(String cnStoreIntroduction) {
        this.cnStoreIntroduction = cnStoreIntroduction == null ? null : cnStoreIntroduction.trim();
    }

    public String getUsStoreName() {
        return usStoreName;
    }

    public void setUsStoreName(String usStoreName) {
        this.usStoreName = usStoreName == null ? null : usStoreName.trim();
    }

    public String getUsStoreCity() {
        return usStoreCity;
    }

    public void setUsStoreCity(String usStoreCity) {
        this.usStoreCity = usStoreCity == null ? null : usStoreCity.trim();
    }

    public String getUsStoreAddress() {
        return usStoreAddress;
    }

    public void setUsStoreAddress(String usStoreAddress) {
        this.usStoreAddress = usStoreAddress == null ? null : usStoreAddress.trim();
    }

    public String getUsStorePicture() {
        return usStorePicture;
    }

    public void setUsStorePicture(String usStorePicture) {
        this.usStorePicture = usStorePicture == null ? null : usStorePicture.trim();
    }

    public String getUsStoreIntroduction() {
        return usStoreIntroduction;
    }

    public void setUsStoreIntroduction(String usStoreIntroduction) {
        this.usStoreIntroduction = usStoreIntroduction == null ? null : usStoreIntroduction.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone == null ? null : storePhone.trim();
    }

    public String getStoreUserName() {
        return storeUserName;
    }

    public void setStoreUserName(String storeUserName) {
        this.storeUserName = storeUserName == null ? null : storeUserName.trim();
    }

    public String getStorePasswd() {
        return storePasswd;
    }

    public void setStorePasswd(String storePasswd) {
        this.storePasswd = storePasswd == null ? null : storePasswd.trim();
    }

    public String getIsSend() {
        return isSend;
    }

    public void setIsSend(String isSend) {
        this.isSend = isSend == null ? null : isSend.trim();
    }

    public String getReserveBeginTime() {
        return reserveBeginTime;
    }

    public void setReserveBeginTime(String reserveBeginTime) {
        this.reserveBeginTime = reserveBeginTime == null ? null : reserveBeginTime.trim();
    }

    public String getReserveEndTime() {
        return reserveEndTime;
    }

    public void setReserveEndTime(String reserveEndTime) {
        this.reserveEndTime = reserveEndTime == null ? null : reserveEndTime.trim();
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