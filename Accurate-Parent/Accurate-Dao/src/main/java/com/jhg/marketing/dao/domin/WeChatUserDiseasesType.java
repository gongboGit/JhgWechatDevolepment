package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lxl
 * @since 2019/4/8 9:03
 */
@Data
@Table(name = "wechat_user_diseases_type")
public class WeChatUserDiseasesType {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    @Column(name = "wechat_user_id")
    private Integer weChatUserId;

    @Column(name = "diseases_type_id")
    private Integer diseasesTypeId;
}
