package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.WeChatFansTag;
import com.jhg.marketing.dao.domin.WeChatTag;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface WeChatFansTagMapper extends Mapper<WeChatFansTag> {

    @Insert({
            "<script>" +
                    "INSERT INTO wechat_fans_tag(open_id,tag_id) " +
                    "VALUES " +
                    "<foreach collection=\"list\" separator=\",\" item=\"item\">" +
                    "(#{item},#{id})"+
                    "</foreach>" +
            "</script>"
    })
    Integer batchInsert(@Param("list") List<String> openIdList, @Param("id") Integer id);

    @Update({
            "TRUNCATE TABLE wechat_fans_tag"
    })
    @Options(useGeneratedKeys = false)
    Integer truncate();

    @Select({
            "SELECT tag_id " +
                    "FROM wechat_fans_tag " +
                    "WHERE open_id = #{openId}"
    })
    List<Integer> getTagArr(String openId);

    @Select({
            "SELECT stuff((" +
                    "SELECT ',' + wt.name " +
                    "FROM wechat_fans_tag wft,wechat_tag wt " +
                    "where wft.tag_id = wt.id " +
                    "and wft.open_id = #{openId} " +
                    "for xml path('')" +
                    "),1,1,'')"
    })
    String getTagStr(String openId);

    @Insert({
            "INSERT INTO wechat_fans_tag(open_id,tag_id) " +
                    "VALUES(#{openId},#{tagId})"
    })
    @Options(useGeneratedKeys = false)
    Integer insertWeChatFansTag(WeChatFansTag weChatFansTag);
}
