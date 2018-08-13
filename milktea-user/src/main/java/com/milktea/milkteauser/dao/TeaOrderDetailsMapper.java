package com.milktea.milkteauser.dao;

import org.apache.ibatis.annotations.Select;

import com.milktea.milkteauser.domain.TeaOrderDetails;

public interface TeaOrderDetailsMapper {
    int deleteByPrimaryKey(String orderDetailId);

    int insert(TeaOrderDetails record);

    int insertSelective(TeaOrderDetails record);

    TeaOrderDetails selectByPrimaryKey(String orderDetailId);

    int updateByPrimaryKeySelective(TeaOrderDetails record);

    int updateByPrimaryKey(TeaOrderDetails record);
    
    @Select("select TEA_ORDERDETAILS_SEQ.nextval from dual")
    String getOrderDetailsSeq();
}