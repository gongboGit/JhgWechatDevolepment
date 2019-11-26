package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.WeChatUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface WeChatUserMapper extends tk.mybatis.mapper.common.Mapper<WeChatUser> {

    @Update({
            "UPDATE wechat_user " +
                    "SET enable = #{enable} " +
                    "WHERE id_card = #{idCard}"
    })
    Integer updateHospitalCardStatus(@Param("idCard") String idCard, @Param("enable") Integer enable);

    @Update({
            "UPDATE wechat_user " +
                    "SET main_flag = 1 " +
                    "WHERE id_card = #{idCard}"
    })
    Integer updateHospitalCardMainFlagByIdCard(String idCard);

    @Update({
            "UPDATE wechat_user " +
                    "SET main_flag = 0 " +
                    "WHERE user_id = #{userId} " +
                    "AND id_card != #{idCard}"
    })
    Integer updateHospitalCardMainFlagByUserId(@Param("userId") Integer userId, @Param("idCard") String idCard);

    @Select({
            "SELECT wu.*" +
                    "FROM wechat_user wu,sys_user su " +
                    "WHERE wu.user_id = su.id " +
                    "AND su.open_id = #{openId} " +
                    "AND wu.enable = 0"
    })
    List<WeChatUser> listPatient(String openId);
}
