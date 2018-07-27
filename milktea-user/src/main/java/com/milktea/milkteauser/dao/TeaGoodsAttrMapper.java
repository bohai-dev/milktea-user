package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaGoodsAttr;
import com.milktea.milkteauser.domain.TeaGoodsAttrKey;

public interface TeaGoodsAttrMapper {
    int deleteByPrimaryKey(TeaGoodsAttrKey key);

    int insert(TeaGoodsAttr record);

    int insertSelective(TeaGoodsAttr record);

    TeaGoodsAttr selectByPrimaryKey(TeaGoodsAttrKey key);

    int updateByPrimaryKeySelective(TeaGoodsAttr record);

    int updateByPrimaryKey(TeaGoodsAttr record);
}