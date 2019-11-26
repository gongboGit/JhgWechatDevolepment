package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.WeChatUserDiseasesType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.ArrayList;
import java.util.List;


public interface WeChatUserDiseasesTypeMapper extends Mapper<WeChatUserDiseasesType> {

    @Select({
            "SELECT stuff((" +
                    "SELECT ',' + dt.name " +
                    "FROM wechat_user_diseases_type wudt,diseases_type dt " +
                    "where wudt.diseases_type_id = dt.id " +
                    "and wudt.wechat_user_id = #{weChatUserId} " +
                    "for xml path('')" +
                    "),1,1,'')"
    })
    String getDiseasesTypeStr(Integer weChatUserId);

    @Select({
            "SELECT diseases_type_id " +
                    "FROM wechat_user_diseases_type " +
                    "WHERE wechat_user_id = #{weChatUserId}"
    })
    List<Integer> getDiseasesTypeIdArr(Integer weChatUserId);


    @Insert({
            "<script>" +
                    "INSERT INTO wechat_user_diseases_type " +
                    "VALUES " +
                    "<foreach collection=\"list\" separator=\",\" item=\"item\">" +
                    "	(#{item.weChatUserId},#{item.diseasesTypeId})" +
                    "</foreach>" +
            "</script>"
    })
    Integer insertWeChatUserDiseasesType(@Param("list")ArrayList<WeChatUserDiseasesType> weChatUserDiseasesTypes);
}
