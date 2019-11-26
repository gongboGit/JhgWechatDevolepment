package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="wechat_fans_tag")
public class WeChatFansTag {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "tag_id")
    private Integer tagId;

}
