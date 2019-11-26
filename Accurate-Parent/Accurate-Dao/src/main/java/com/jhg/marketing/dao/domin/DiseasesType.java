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
@Table(name = "diseases_type")
public class DiseasesType {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    private String name;

}
