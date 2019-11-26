package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.MessageRecord;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface MessageRecordMapper extends Mapper<MessageRecord> {

    @Select({
            "SELECT wmr.id,wmr.create_time createTime, wu.true_name trueName, " +
                    "wtm.title " +
                    "FROM wechat_message_record wmr, wechat_user wu, wechat_template_message wtm " +
                    "WHERE wmr.wechat_user_id = wu.id " +
                    "AND wmr.message_id = wtm.id"
    })
    List<MessageRecord> listMessageRecord();
}
