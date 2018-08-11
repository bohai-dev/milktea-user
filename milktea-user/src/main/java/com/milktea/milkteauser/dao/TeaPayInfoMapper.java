package com.milktea.milkteauser.dao;

import com.milktea.milkteauser.domain.TeaPayInfo;
import org.apache.ibatis.annotations.Select;

public interface TeaPayInfoMapper {
    int deleteByPrimaryKey(String payId);

    int insert(TeaPayInfo record);

    int insertSelective(TeaPayInfo record);

    TeaPayInfo selectByPrimaryKey(String payId);

    int updateByPrimaryKeySelective(TeaPayInfo record);

    int updateByPrimaryKey(TeaPayInfo record);

    @Select(value="select TEA_PAYINFO_ID_SEQ.NEXTVAL from dual")
    String generateClassId();
}