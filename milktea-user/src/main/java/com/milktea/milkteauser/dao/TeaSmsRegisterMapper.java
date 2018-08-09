package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaSmsRegister;

public interface TeaSmsRegisterMapper {
    int insert(TeaSmsRegister record);

    int insertSelective(TeaSmsRegister record);
}