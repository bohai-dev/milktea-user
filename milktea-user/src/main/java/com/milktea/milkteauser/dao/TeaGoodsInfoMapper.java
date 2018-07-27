package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaGoodsInfo;

public interface TeaGoodsInfoMapper {
    int deleteByPrimaryKey(String goodsId);

    int insert(TeaGoodsInfo record);

    int insertSelective(TeaGoodsInfo record);

    TeaGoodsInfo selectByPrimaryKey(String goodsId);

    int updateByPrimaryKeySelective(TeaGoodsInfo record);

    int updateByPrimaryKey(TeaGoodsInfo record);
}