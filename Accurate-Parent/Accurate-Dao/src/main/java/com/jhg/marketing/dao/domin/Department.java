package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "department")
public class Department implements Serializable {
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    private String name;

}
