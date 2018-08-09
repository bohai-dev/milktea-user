package com.milktea.milkteauser.vo;

/**
 * 分类商品请求参数
 * @author caoxx
 *
 */
public class ClassGoodsRequestVo {
    
    /**
     * 店铺编号
     */
    private String storeNo;
    
    /**
     * 分类类型 0：普通分类 1：特色分类
     */
    private String classType;

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
    
    

}
