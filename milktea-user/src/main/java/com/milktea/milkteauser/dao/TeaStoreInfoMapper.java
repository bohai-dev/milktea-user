package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaStoreInfo;

public interface TeaStoreInfoMapper {
    int deleteByPrimaryKey(String storeNo);

    int insert(TeaStoreInfo record);

    int insertSelective(TeaStoreInfo record);

    TeaStoreInfo selectByPrimaryKey(String storeNo);

    int updateByPrimaryKeySelective(TeaStoreInfo record);

    int updateByPrimaryKey(TeaStoreInfo record);
}