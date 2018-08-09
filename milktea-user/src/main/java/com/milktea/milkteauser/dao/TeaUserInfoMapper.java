package com.milktea.milkteauser.dao;

import java.util.List;

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
    
    @Select("select * from TEA_USER_INFO where TELEPHONE = #{telephone}")
    TeaUserInfo selectByTelephone(@Param("telephone") String telephone);
    
    @Select("select * from TEA_USER_INFO where WEIXIN_OPENID = #{weixinOpenId}")
    TeaUserInfo selectByWeixinOpenId(@Param("weixinOpenId") String weixinOpenId);
    
    @Update("update TEA_USER_INFO set WEIXIN_OPENID = #{weixinOpenId},WEIXIN_ID = #{weixinId} where TELEPHONE = #{telephone}")
    TeaUserInfo bindTelephoneWeixinOpenid(@Param("telephone") String telephone,@Param("weixinOpenId") String weixinOpenId,@Param("weixinId") String weixinId);
}