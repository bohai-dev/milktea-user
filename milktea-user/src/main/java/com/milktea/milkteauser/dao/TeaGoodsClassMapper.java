package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaGoodsClass;
import com.milktea.milkteauser.domain.TeaGoodsClassKey;

public interface TeaGoodsClassMapper {
    int deleteByPrimaryKey(TeaGoodsClassKey key);

    int insert(TeaGoodsClass record);

    int insertSelective(TeaGoodsClass record);

    TeaGoodsClass selectByPrimaryKey(TeaGoodsClassKey key);

    int updateByPrimaryKeySelective(TeaGoodsClass record);

    int updateByPrimaryKey(TeaGoodsClass record);
}