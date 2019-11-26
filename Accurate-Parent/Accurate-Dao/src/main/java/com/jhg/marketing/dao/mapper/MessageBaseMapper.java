package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.material.MessageBase;
import com.jhg.marketing.dao.domin.material.MessageNews;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageBaseMapper extends Mapper<MessageBase> {

    @Select({
            "SELECT id,mb.media_id" +
                    "FROM message_relation mr,message_base mb " +
                    "WHERE mr.base_id = mb.id " +
                    "AND mr.message_id = #{messageId}"
    })
    MessageBase getMediaId(Integer messageNewsId);

    @Update({
            "UPDATE message_base " +
                    "SET enabled = ${status} " +
                    "WHERE id = (" +
                    "SELECT mb.id " +
                    "FROM message_news mn,message_base mb,message_relation mr " +
                    "WHERE mn.id = mr.message_id " +
                    "AND mb.id = mr.base_id " +
                    "AND mn.id = ${id})"
    })
    Integer updateEnabled(@Param("id")Integer id, @Param("status")Boolean status);
}
