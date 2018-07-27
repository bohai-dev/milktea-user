package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaAdmin;

public interface TeaAdminMapper {
    int insert(TeaAdmin record);

    int insertSelective(TeaAdmin record);
}