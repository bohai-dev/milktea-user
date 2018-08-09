package com.milktea.milkteauser.vo;

import java.math.BigDecimal;
import java.util.List;

import com.milktea.milkteauser.domain.TeaAttributesInfo;
import com.milktea.milkteauser.domain.TeaClassInfo;



/**
 * 商品信息
 * @author caoxx
 *
 */
public class GoodsInfoVo {
    
    /**
     * 商品Id
     */
    private String goodsId;

    /**
     * 店铺编号
     */
    private String storeNo;

    /**
     * 商品名称（中文）
     */
    private String cnGoodsName;

    /**
     * 商品介绍（中文）
     */
    private String cnGoodsIntroduction;

    /**
     * 商品图片路径（中文）
     */
    private String cnGoodsPictureBig;

    /**
     * 商品图片路径（中文）
     */
    private String cnGoodsPictureRound;

    /**
     * 商品名称（英文）
     */
    private String usGoodsName;

    /**
     * 商品介绍（英文）
     */
    private String usGoodsIntroduction;

    /**
     * 商品图片路径（英文）
     */
    private String usGoodsPictureBig;

    /**
     * 商品图片路径（英文）
     */
    private String usGoodsPictureRound;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 商品状态 0：不在售 1：在售
     */
    private String goodsStatus;

    /**
     * 商品库存
     */
    private BigDecimal goodsStock;

    /**
     * 商品属性
     */
    private List<TeaAttributesInfo> goodsAttrs;
    
    /**
     * 商品分类
     */
    private List<TeaClassInfo> classInfos;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getCnGoodsName() {
        return cnGoodsName;
    }

    public void setCnGoodsName(String cnGoodsName) {
        this.cnGoodsName = cnGoodsName;
    }

    public String getCnGoodsIntroduction() {
        return cnGoodsIntroduction;
    }

    public void setCnGoodsIntroduction(String cnGoodsIntroduction) {
        this.cnGoodsIntroduction = cnGoodsIntroduction;
    }

    public String getCnGoodsPictureBig() {
        return cnGoodsPictureBig;
    }

    public void setCnGoodsPictureBig(String cnGoodsPictureBig) {
        this.cnGoodsPictureBig = cnGoodsPictureBig;
    }

    public String getCnGoodsPictureRound() {
        return cnGoodsPictureRound;
    }

    public void setCnGoodsPictureRound(String cnGoodsPictureRound) {
        this.cnGoodsPictureRound = cnGoodsPictureRound;
    }

    public String getUsGoodsName() {
        return usGoodsName;
    }

    public void setUsGoodsName(String usGoodsName) {
        this.usGoodsName = usGoodsName;
    }

    public String getUsGoodsIntroduction() {
        return usGoodsIntroduction;
    }

    public void setUsGoodsIntroduction(String usGoodsIntroduction) {
        this.usGoodsIntroduction = usGoodsIntroduction;
    }

    public String getUsGoodsPictureBig() {
        return usGoodsPictureBig;
    }

    public void setUsGoodsPictureBig(String usGoodsPictureBig) {
        this.usGoodsPictureBig = usGoodsPictureBig;
    }

    public String getUsGoodsPictureRound() {
        return usGoodsPictureRound;
    }

    public void setUsGoodsPictureRound(String usGoodsPictureRound) {
        this.usGoodsPictureRound = usGoodsPictureRound;
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
        this.goodsStatus = goodsStatus;
    }

    public BigDecimal getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(BigDecimal goodsStock) {
        this.goodsStock = goodsStock;
    }

    public List<TeaAttributesInfo> getGoodsAttrs() {
        return goodsAttrs;
    }

    public void setGoodsAttrs(List<TeaAttributesInfo> goodsAttrs) {
        this.goodsAttrs = goodsAttrs;
    }

    public List<TeaClassInfo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(List<TeaClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

}
