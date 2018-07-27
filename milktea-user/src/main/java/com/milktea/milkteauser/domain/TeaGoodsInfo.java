package com.milktea.milkteauser.domain;

import java.math.BigDecimal;
import java.util.Date;

public class TeaGoodsInfo {
    private String goodsId;

    private String storeNo;

    private String cnGoodsName;

    private String cnGoodsIntroduction;

    private String cnGoodsPictureBig;

    private String cnGoodsPictureRound;

    private String usGoodsName;

    private String usGoodsIntroduction;

    private String usGoodsPictureBig;

    private String usGoodsPictureRound;

    private BigDecimal goodsPrice;

    private String goodsStatus;

    private BigDecimal goodsStock;

    private Date updateTime;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo == null ? null : storeNo.trim();
    }

    public String getCnGoodsName() {
        return cnGoodsName;
    }

    public void setCnGoodsName(String cnGoodsName) {
        this.cnGoodsName = cnGoodsName == null ? null : cnGoodsName.trim();
    }

    public String getCnGoodsIntroduction() {
        return cnGoodsIntroduction;
    }

    public void setCnGoodsIntroduction(String cnGoodsIntroduction) {
        this.cnGoodsIntroduction = cnGoodsIntroduction == null ? null : cnGoodsIntroduction.trim();
    }

    public String getCnGoodsPictureBig() {
        return cnGoodsPictureBig;
    }

    public void setCnGoodsPictureBig(String cnGoodsPictureBig) {
        this.cnGoodsPictureBig = cnGoodsPictureBig == null ? null : cnGoodsPictureBig.trim();
    }

    public String getCnGoodsPictureRound() {
        return cnGoodsPictureRound;
    }

    public void setCnGoodsPictureRound(String cnGoodsPictureRound) {
        this.cnGoodsPictureRound = cnGoodsPictureRound == null ? null : cnGoodsPictureRound.trim();
    }

    public String getUsGoodsName() {
        return usGoodsName;
    }

    public void setUsGoodsName(String usGoodsName) {
        this.usGoodsName = usGoodsName == null ? null : usGoodsName.trim();
    }

    public String getUsGoodsIntroduction() {
        return usGoodsIntroduction;
    }

    public void setUsGoodsIntroduction(String usGoodsIntroduction) {
        this.usGoodsIntroduction = usGoodsIntroduction == null ? null : usGoodsIntroduction.trim();
    }

    public String getUsGoodsPictureBig() {
        return usGoodsPictureBig;
    }

    public void setUsGoodsPictureBig(String usGoodsPictureBig) {
        this.usGoodsPictureBig = usGoodsPictureBig == null ? null : usGoodsPictureBig.trim();
    }

    public String getUsGoodsPictureRound() {
        return usGoodsPictureRound;
    }

    public void setUsGoodsPictureRound(String usGoodsPictureRound) {
        this.usGoodsPictureRound = usGoodsPictureRound == null ? null : usGoodsPictureRound.trim();
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus == null ? null : goodsStatus.trim();
    }

    public BigDecimal getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(BigDecimal goodsStock) {
        this.goodsStock = goodsStock;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}