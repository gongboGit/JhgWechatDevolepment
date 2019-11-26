package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @author lxl
 * @since 2019/4/8 9:03
 */
@Data
@Table(name = "wechat_template_message")
public class TemplateMessage {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "template_id")
    private String templateId;

    private String url;

    private String appId;

    private String pagePath;
    
    private String title;

    @Column(name = "template_title")
    private String templateTitle;

    @Column(name = "diseases_type_id")
    private Integer diseasesTypeId;

    @Column(name = "create_time")
    private Date createTime;

    @Transient
    private String diseasesType;

}
