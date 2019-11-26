package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.ExpertInformation;
import com.jhg.marketing.dao.domin.SysUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface ExpertInformationMapper extends Mapper<ExpertInformation> {


    @Select({
            "SELECT ei.*" +
                    "FROM expert_information ei, department d " +
                    "WHERE ei.department_id = d.id " +
                    "AND d.name = #{departmentName}"
    })
    List<ExpertInformation> listExpertInfoByDepartmentName(String departmentName);

    @Select({
            "SELECT ei.*,d.name departmentName " +
                    "FROM expert_information ei,department d " +
                    "WHERE ei.department_id = d.id"
    })
    List<ExpertInformation> listExpertInformation();

    @Delete({
            "<script>" +
                    "DELETE FROM expert_information " +
                    "WHERE id IN " +
                    "<foreach collection=\"idList\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" +
                    "#{item}" +
                    "</foreach>" +
            "</script>"
    })
    Integer deleteExpertInformation(@Param("idList") List<String> ids);
}
