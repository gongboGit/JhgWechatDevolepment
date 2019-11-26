package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.TemplateMessageData;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface TemplateMessageDataMapper extends Mapper<TemplateMessageData> {

    @Insert({
            "<script>" +
                    "INSERT INTO wechat_template_message_data(message_id," +
                    "key_name,key_value,color)" +
                    "VALUES "+
                    "<foreach collection=\"list\" separator=\",\" item=\"item\">" +
                    "(#{item.messageId},#{item.keyName}," +
                    "#{item.keyValue},#{item.color})"+
                    "</foreach>" +
            "</script>"
    })
    Integer bantchInsert(@Param("list") List<TemplateMessageData> paramList);
}
