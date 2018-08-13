package com.milktea.milkteauser.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.milktea.milkteauser.domain.TeaSmsRegister;

public interface TeaSmsRegisterMapper {
    int insert(TeaSmsRegister record);

    int insertSelective(TeaSmsRegister record);
    
    @Insert("insert into TEA_SMS_REGISTER values (#{telephone},#{code},sysdate,0)")
    int insertSMSReg(@Param("telephone") String telephone,@Param("code") String code);
    
    @Select("select IDENTIFY_CODE from (select IDENTIFY_CODE from TEA_SMS_REGISTER where TELEPHONE = #{telephone} order by IDENTIFY_TIME desc) where rownum = 1")
    String getIdentifyCode(@Param("telephone") String telephone);
    
    @Select("select case when sysdate+numtodsinterval(-2,'minute') <= IDENTIFY_TIME then 1 else 98 end from (select IDENTIFY_TIME from TEA_SMS_REGISTER where TELEPHONE = #{telephone} order by IDENTIFY_TIME desc) where rownum = 1")
    int checkIdentifyTime(@Param("telephone") String telephone);
}