package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.TemplateMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface TemplateMessageMapper extends Mapper<TemplateMessage> {

    @Insert({
            "<script>" +
                    "INSERT INTO template_message(template_id,title,primary_industry," +
                        "deputy_industry,content,example) " +
                        "VALUES "+
                        "<foreach collection=\"list\" separator=\",\" item=\"item\">" +
                            "(#{item.template_id},#{item.title}," +
                            "#{item.primary_industry},#{item.deputy_industry},#{item.content}," +
                            "#{item.example})"+
                        "</foreach>" +
            "</script>"
    })
    Integer bantchInsert(@Param("list")List<Map> list);

    @Select({
            "<script>" +
                    "SELECT wtm.*,dt.name diseasesType " +
                    "FROM wechat_template_message wtm LEFT JOIN diseases_type dt " +
                    "ON wtm.diseases_type_id = dt.id " +
                    "<if test=\"title != null and title != ''\">" +
                        "WHERE wtm.title like '%'+ #{title} +'%' " +
                    "</if>" +
            "</script>"
    })
    List<TemplateMessage> listMessage(@Param("title") String title);
}
