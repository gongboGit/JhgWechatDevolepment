package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@Table(name="wechat_user")
public class WeChatUser {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "id_card")
    private String idCard;

    @Column(name = "main_flag")
    private Integer mainFlag;

    @Column(name = "hospitalization_number")
    private String hospitalizationNumber;

    @Column(name = "true_name")
    private String trueName;

    private String telephone;

    @Transient
    private Integer diseasesTypeId;

    private Boolean enable;

}
