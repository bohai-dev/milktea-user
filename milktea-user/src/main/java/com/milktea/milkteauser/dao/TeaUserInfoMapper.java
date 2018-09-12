package com.milktea.milkteauser.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.milktea.milkteauser.domain.TeaUserInfo;

public interface TeaUserInfoMapper {
    int deleteByPrimaryKey(String userNo);

    int insert(TeaUserInfo record);

    int insertSelective(TeaUserInfo record);

    TeaUserInfo selectByPrimaryKey(String userNo);

    int updateByPrimaryKeySelective(TeaUserInfo record);

    int updateByPrimaryKey(TeaUserInfo record);
    
    @Select("select * from TEA_USER_INFO where TELEPHONE = #{telephone} and USER_PASSWORD = #{userPassword}")
    TeaUserInfo checkUserLogin(@Param("telephone") String telephone,@Param("userPassword") String userPassword);
    
    @Select("select TEA_CUSTNO_SEQ.nextval from dual")
    String getNewCustSeq();
    
    @Select("select * from TEA_USER_INFO where TELEPHONE = #{telephone}")
    TeaUserInfo selectByTelephone(@Param("telephone") String telephone);
    
    @Select("select * from TEA_USER_INFO where WEIXIN_OPENID = #{weixinOpenId}")
    TeaUserInfo selectByWeixinOpenId(@Param("weixinOpenId") String weixinOpenId);
    
    @Update("update TEA_USER_INFO set WEIXIN_OPENID = #{weixinOpenId} where TELEPHONE = #{telephone}")
    int bindTelephoneWeixinOpenid(@Param("telephone") String telephone,@Param("weixinOpenId") String weixinOpenId);
    
    @Update("update TEA_USER_INFO set USER_PASSWORD = #{userPassword} where TELEPHONE = #{telephone}")
    int modifyUserPassword(@Param("telephone") String telephone,@Param("userPassword") String userPassword);
    
    @Update("update TEA_USER_INFO set POINTS = POINTS + #{point} where USER_NO = #{userNo}")
    int modifyPoint(@Param("userNo") String userNo,@Param("point") BigDecimal point);
    
}