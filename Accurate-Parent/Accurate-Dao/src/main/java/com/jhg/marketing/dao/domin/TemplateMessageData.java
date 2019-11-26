package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lxl
 * @since 2019/4/8 9:03
 */
@Data
@Table(name = "wechat_template_message_data")
public class TemplateMessageData {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "message_id")
    private Integer messageId;

    @Column(name = "key_name")
    private String keyName;

    @Column(name = "key_value")
    private String keyValue;

    private String color;

}
