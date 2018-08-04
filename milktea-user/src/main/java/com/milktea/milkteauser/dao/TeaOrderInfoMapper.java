package com.milktea.milkteauser.dao;

import org.apache.ibatis.annotations.Select;

import com.milktea.milkteauser.domain.TeaOrderInfo;

public interface TeaOrderInfoMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(TeaOrderInfo record);

    int insertSelective(TeaOrderInfo record);

    TeaOrderInfo selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(TeaOrderInfo record);

    int updateByPrimaryKey(TeaOrderInfo record);
    
    @Select("select to_char(sysdate,'YYYYMMDD_') || 'A_' ||TEA_CUSTORDER_SEQ.nextval from dual")
    String getCustOrderSeq();
}