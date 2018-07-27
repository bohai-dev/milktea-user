package com.milktea.milkteauser.domain;

public class TeaGoodsClass extends TeaGoodsClassKey {
    private String deleteFlag;

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag == null ? null : deleteFlag.trim();
    }
}