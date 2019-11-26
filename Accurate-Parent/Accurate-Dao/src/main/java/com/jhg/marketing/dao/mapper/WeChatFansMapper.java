package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.WeChatFans;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import javax.persistence.Id;
import java.util.List;
import java.util.Map;


public interface WeChatFansMapper extends Mapper<WeChatFans> {

    @Insert({
            "<script>" +
                    "INSERT INTO wechat_fans(open_id,subscribe,subscribe_time," +
                    "subscribe_scene,nick_name,sex,country,province,city,language," +
                    "head_img_url,union_id,remark,group_id,qr_scene," +
                    "qr_scene_str,create_time) " +
                    "VALUES " +
                    "<foreach collection=\"list\" separator=\",\" item=\"item\">" +
                    "(#{item.openid},#{item.subscribe},DATEADD(s,#{item.subscribe_time},'1970-01-01 08:00:00'),#{item.subscribe_scene}," +
                    "#{item.nickname},#{item.sex},#{item.country},#{item.province}," +
                    "#{item.city},#{item.language},#{item.headimgurl},#{item.unionid}," +
                    "#{item.remark},#{item.groupid},#{item.qr_scene}," +
                    "#{item.qr_scene_str},GETDATE())" +
                    "</foreach>" +
                    "</script>"
    })
    Integer insertWeChatFansList(@Param("list") List<Map> list);

    @Insert({
            "INSERT INTO wechat_fans(open_id,subscribe,subscribe_time," +
                    "subscribe_scene,nick_name,sex,country,province,city,language," +
                    "head_img_url,union_id,remark,group_id,qr_scene," +
                    "qr_scene_str,create_time) " +
                    "VALUES " +
                    "(#{item.openid},#{item.subscribe},DATEADD(s,#{item.subscribe_time},'1970-01-01 08:00:00'),#{item.subscribe_scene}," +
                    "#{item.nickname},#{item.sex},#{item.country},#{item.province}," +
                    "#{item.city},#{item.language},#{item.headimgurl},#{item.unionid}," +
                    "#{item.remark},#{item.groupid},#{item.qr_scene}," +
                    "#{item.qr_scene_str},GETDATE())"
    })
    @Options(useGeneratedKeys = false)
    Integer insertWeChatFans(@Param("item") Map map);


    @Select({
            "SELECT TOP 1 open_id " +
                    "FROM wechat_fans " +
                    "ORDER BY id DESC"
    })
    String getLastOpenId();

    @Select({
            "<script>" +
                    "SELECT * " +
                    "FROM wechat_fans " +
                    "where 1 = 1 " +
                    "<if test=\"nickName != null and nickName != ''\">" +
                    "AND (CAST(nick_name AS VARCHAR) like '%'+#{nickName}+'%' " +
                    "OR remark like '%'+#{nickName}+'%') " +
                    "</if>" +
                    "</script>"
    })
    List<WeChatFans> listFansByCondition(@Param("nickName") String nickName);

    @Select({
            "SELECT wf.id,wf.nick_name,wf.open_id,wf.head_img_url,wf.sex,wf.country," +
                    "wf.province,wf.city,wf.subscribe_time,stuff((" +
                    "SELECT ',' + wt.name " +
                    "FROM wechat_fans_tag wft,wechat_tag wt " +
                    "where wft.tag_id = wt.id " +
                    "and wft.open_id = wf.open_id " +
                    "for xml path('')" +
                    "),1,1,'') tagStr " +
                    "FROM wechat_fans wf "
    })
    List<WeChatFans> litFans();
}
