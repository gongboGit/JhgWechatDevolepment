package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.Department;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface DepartmentMapper extends Mapper<Department> {

    @Select({
            "SELECT name " +
                    "FROM department"
    })
    List<String> listDepartment();

    @Delete({
            "<script>" +
                    "DELETE FROM department " +
                    "WHERE id IN " +
                    "<foreach collection=\"ids\" open=\"(\" close=\")\" separator=\",\" item=\"item\">" +
                    "#{item}" +
                    "</foreach>" +
            "</script>"
    })
    Integer deleteDepartment(@Param("ids") List<Integer> ids);
}
