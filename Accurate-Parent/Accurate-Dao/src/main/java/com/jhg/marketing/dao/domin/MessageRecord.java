package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

/**
 * @author lxl
 * @since 2019/4/8 9:03
 */
@Data
@Table(name = "wechat_message_record")
public class MessageRecord {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "wechat_user_id")
    private Integer weChatUserId;

    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "wechat_message_id")
    private Long weChatMessageId;

    @Column(name = "create_time")
    private Date createTime;

    @Transient
    private String trueName;

    @Transient
    private String title;
}
