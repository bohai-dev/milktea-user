package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaPayInfo;

public interface TeaPayInfoMapper {
    int deleteByPrimaryKey(String payId);

    int insert(TeaPayInfo record);

    int insertSelective(TeaPayInfo record);

    TeaPayInfo selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(TeaPayInfo record);

    int updateByPrimaryKey(TeaPayInfo record);
}