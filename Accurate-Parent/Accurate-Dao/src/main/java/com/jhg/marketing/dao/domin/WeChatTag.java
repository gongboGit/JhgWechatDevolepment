package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Data
@Table(name="wechat_tag")
public class WeChatTag {

    @Id
    @Column(name = "id", updatable = false)
    private Integer id;

    private String name;

}
