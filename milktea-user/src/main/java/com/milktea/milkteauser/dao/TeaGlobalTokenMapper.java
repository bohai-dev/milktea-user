package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaGlobalToken;

public interface TeaGlobalTokenMapper {
    int deleteByPrimaryKey(String token);

    int insert(TeaGlobalToken record);

    int insertSelective(TeaGlobalToken record);

    TeaGlobalToken selectByPrimaryKey(String token);

    int updateByPrimaryKeySelective(TeaGlobalToken record);

    int updateByPrimaryKey(TeaGlobalToken record);
}