package com.jhg.marketing.web.admin.controller;

import com.jhg.marketing.dao.domin.Department;
import com.jhg.marketing.dao.mapper.DepartmentMapper;
import com.jhg.marketing.web.admin.service.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专家信息接口
 *
 * @author lxl
 * @since 5/28/2019 4:09 PM
 */
@Slf4j
@RestController
@RequestMapping("admin/department")
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取科室列表
     *
     * @return
     */
    @RequestMapping("listDepartment")
    public Message listDepartment() {
        return Message.success("获取数据成功！", departmentMapper.selectAll());
    }

    /**
     * 新增科室信息
     *
     * @param departmentName
     * @return
     */
    @RequestMapping("insertDepartment")
    public Message insertDepartment(String departmentName) {
        return Message.success("新增成功！", departmentMapper.insert(new Department() {{
            setName(departmentName);
        }}));
    }

    /**
     * 删除科室信息
     *
     * @param id
     * @return
     */
    @RequestMapping("deleteDepartment")
    public Message deleteDepartment(Integer id) {

        return Message.success("删除成功！", departmentMapper.deleteByPrimaryKey(id));
//        return Message.success("删除成功！", departmentMapper.deleteDepartment(ids));
    }

}
