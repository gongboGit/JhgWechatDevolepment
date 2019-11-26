package com.jhg.marketing.dao.domin;

import com.jhg.marketing.common.Explain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Data
@Table(name = "expert_information")
public class ExpertInformation implements Serializable {

    @Explain(value = "id", isEdit = false)
    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    private String name;

    private String description;

    @Explain("头像")
    @Column(name = "head_img_url")
    private String headImgUrl;

    @Explain("是否在显示在医院信息页面")
    @Column(name = "show_index")
    private Boolean showIndex;

    @Column(name = "department_id")
    private Integer departmentId;

    @Transient
    private String departmentName;

}
