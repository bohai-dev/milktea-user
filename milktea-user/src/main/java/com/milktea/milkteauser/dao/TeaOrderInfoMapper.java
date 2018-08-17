package com.milktea.milkteauser.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.milktea.milkteauser.domain.TeaOrderInfo;
import com.milktea.milkteauser.domain.TeaUserInfo;

public interface TeaOrderInfoMapper {
    int deleteByPrimaryKey(String orderNo);

    int insert(TeaOrderInfo record);

    int insertSelective(TeaOrderInfo record);

    TeaOrderInfo selectByPrimaryKey(String orderNo);

    int updateByPrimaryKeySelective(TeaOrderInfo record);

    int updateByPrimaryKey(TeaOrderInfo record);
    
    @Select("select to_char(sysdate,'YYYYMMDD_') || 'A_' ||TEA_CUSTORDER_SEQ.nextval from dual")
    String getCustOrderSeq();

    @Update("update TEA_ORDER_INFO set PAY_STATUS = #{orderStatus},UPDATE_TIME = sysdate where ORDER_NO = #{orderNo}")
    int modifyOrderStatus(@Param("orderNo") String orderNo,@Param("orderStatus") String orderStatus);
    
    @Update("update TEA_ORDER_INFO set ORDER_TIME = to_date(#{orderTime},'yyyy/mm/dd hh24:mi:ss'),REMARK = #{remark},ORDER_TYPE = #{orderType} where ORDER_NO = #{orderNo}")
    int finishPayModfiyOrder(@Param("orderNo") String orderNo,@Param("remark") String remark,@Param("orderTime") String orderTime,@Param("orderType") String orderType);
    
}
