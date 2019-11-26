package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.material.MessageNews;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MessageNewsMapper extends Mapper<MessageNews> {

    @Select({
            "SELECT mn.id,mn.author,mn.title,mn.digest,mn.show_cover_pic,mn.content,mn.thumb_url thumbUrl," +
                    "mb.enabled enabled " +
                    "FROM message_news mn,message_relation mr,message_base mb " +
                    "WHERE mn.id = mr.message_id " +
                    "AND mb.id = mr.base_id " +
                    "AND mb.type = 1"
    })
    List<MessageNews> listMessageNews();
}
