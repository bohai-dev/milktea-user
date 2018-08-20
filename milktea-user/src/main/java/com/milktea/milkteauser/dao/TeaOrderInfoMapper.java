package com.milktea.milkteauser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    
    @Select("select * from TEA_ORDER_INFO where TELEPHONE = #{orderStatus}")
    List<TeaOrderInfo> findOrderByTelephone(@Param("telephone") String telephone,@Param("flag") String flag);


    @Update("update TEA_ORDER_INFO set ORDER_STATUS = #{orderStatus},UPDATE_TIME = sysdate where ORDER_NO = #{orderNo}")
    int modifyOrderStatus(@Param("orderNo") String orderNo,@Param("orderStatus") String orderStatus);
    
    @Update("update TEA_ORDER_INFO set BOOK_TIME = to_date(#{orderTime},'yyyy/mm/dd hh24:mi:ss'),REMARK = #{remark},ORDER_TYPE = #{orderType} where ORDER_NO = #{orderNo}")
    int finishPayModfiyOrder1(@Param("orderNo") String orderNo,@Param("remark") String remark,@Param("orderTime") String orderTime,@Param("orderType") String orderType);
    
    @Update("update TEA_ORDER_INFO set REMARK = #{remark},ORDER_TYPE = #{orderType} where ORDER_NO = #{orderNo}")
    int finishPayModfiyOrder2(@Param("orderNo") String orderNo,@Param("remark") String remark,@Param("orderType") String orderType);

    //更新订单的支付状态
    @Update("update TEA_ORDER_INFO set PAY_STATUS = #{payStatus},UPDATE_TIME = sysdate where ORDER_NO = #{orderNo}")
    int updatePayStatus(@Param("orderNo")String orderNo,@Param("payStatus")String payStatus);

}
