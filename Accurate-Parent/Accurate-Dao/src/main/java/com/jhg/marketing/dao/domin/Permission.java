package com.jhg.marketing.dao.domin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import com.jhg.marketing.common.Explain;

import lombok.Data;

@Data
@Table(name = "permission")
public class Permission implements Serializable {
    @Id
    @Explain(value = "主键", isShow = false, isEdit = false)
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    private String controller;

    private String method;

    @Explain(value = "标题", required = true)
    private String title;

    @Explain(value = "地址")
    private String url;

    @Explain("图标")
    private String icon;

    @Explain(value = "停用/启用")
    @Column(name = "enabled")
    private Boolean enabled;

    @Explain(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Explain("显示在菜单")
    @Column(name = "is_show")
    private Boolean isShow;

    @Explain(value = "备注", dataType = "textarea")
    private String remark;

    private Integer pid;

    @Explain(value = "类型", dataType = "select",dataTokens = "{\"后台菜单\":1,\"微信菜单\":2}")
    private Integer flag;

    @Explain(value = "是否选中", isEdit = false, isShow = false)
    @Transient
    private String checked;


}
