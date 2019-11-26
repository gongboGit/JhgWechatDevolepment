package com.jhg.marketing.dao.domin;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import com.jhg.marketing.common.Explain;

import lombok.Data;

@Data
@Table(name = "sys_user")
public class SysUser implements Serializable {

    @Explain(value = "id" )
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Explain(value = "登录账号", required = true )
    private String username;

    @Explain(value = "登录密码", required = true )
    private String password;

    @Explain("性别")
    private String gender;

    @Explain("邮箱")
    private String email;

    @Explain("是否启用")
    @Column(name = "enabled")
    private Boolean enabled;

    private Integer type;

    @Explain(value = "创建时间" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "register_time")
    private Date registerTime;

    @Explain(value = "上次登录时间" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "last_login_time")
    @JSONField(format = "yyyy-MM-dd")
    private Date lastLoginTime;

    @Explain(value = "openId"  )
    @Column(name = "open_id")
    private String openId;

    @Explain(value = "头像"  )
    @Column(name = "head_img_url")
    private String headImgUrl;

    @Explain("真实姓名")
    @Transient
    private String trueName;

    @Explain("电话号码")
    @Transient
    private String telephone;

    @Explain(value = "描述"  )
    @Transient
    private String description;

    @Explain(value = "就诊卡是否在显示在医院信息页面"  )
    @Transient
    private Boolean showIndex;

    @Explain(value = "就诊卡是否删除"  )
    @Transient
    private Boolean enable;

    @Explain(value = "身份证号")
    @Transient
    private String idCard;

    @Explain(value = "是否主就诊人"  )
    @Transient
    private Integer mainFlag;

    @Explain(value = "住院号"  )
    @Transient
    private Integer hospitalizationNumber;

    @Explain(value = "记住我"  )
    @Transient
    private boolean rememberMe;

    @Explain(value = "验证码"  )
    @Transient
    private String captcha;

    @Explain(value = "角色ids" )
    @Transient
    private Integer[] roleIdArr;

    @Explain(value = "角色")
    @Transient
    private String sysRoleStr;

    @Explain(value = "病种类型IDs" )
    @Transient
    private Integer[] diseasesTypeIds;

    @Explain(value = "病种类型" )
    @Transient
    private String diseasesTypeStr;

    @Explain(value = "微信用户ID")
    @Transient
    private Integer weChatUserId;

}
