package com.milktea.milkteauser.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 促销活动
 * @author caojia
 *
 */
public class PromotionVo {

    private String promotionId;

    private String cnPromotionName;

    private String cnPromotionIntroduction;

    private String usPromotionName;

    private String usPromotionIntroduction;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private String promotionStatus;

    private List<String> storeNos;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getCnPromotionName() {
        return cnPromotionName;
    }

    public void setCnPromotionName(String cnPromotionName) {
        this.cnPromotionName = cnPromotionName;
    }

    public String getCnPromotionIntroduction() {
        return cnPromotionIntroduction;
    }

    public void setCnPromotionIntroduction(String cnPromotionIntroduction) {
        this.cnPromotionIntroduction = cnPromotionIntroduction;
    }

    public String getUsPromotionName() {
        return usPromotionName;
    }

    public void setUsPromotionName(String usPromotionName) {
        this.usPromotionName = usPromotionName;
    }

    public String getUsPromotionIntroduction() {
        return usPromotionIntroduction;
    }

    public void setUsPromotionIntroduction(String usPromotionIntroduction) {
        this.usPromotionIntroduction = usPromotionIntroduction;
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
        this.promotionStatus = promotionStatus;
    }

    public List<String> getStoreNos() {
        return storeNos;
    }

    public void setStoreNos(List<String> storeNos) {
        this.storeNos = storeNos;
    }
    
}
