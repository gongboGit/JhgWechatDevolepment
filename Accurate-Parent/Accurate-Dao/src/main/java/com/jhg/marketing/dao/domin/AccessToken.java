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
@Table(name = "access_token")
public class AccessToken {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    /**
     * 凭证
     */
    @Column(name = "access_token")
    private String accessToken;

    /**
     * 凭证获取时间
     */
    @Column(name = "create_time")
    private Date createTime;
}
