package com.jhg.marketing.web.admin.controller;

import com.jhg.marketing.dao.domin.ExpertInformation;
import com.jhg.marketing.dao.mapper.ExpertInformationMapper;
import com.jhg.marketing.web.admin.service.Message;
import com.jhg.marketing.web.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * 专家信息接口
 *
 * @author lxl
 * @since 5/28/2019 4:09 PM
 */
@Slf4j
@RestController
@RequestMapping("admin/expertInformation")
public class ExpertInformationController {

    @Autowired
    private ExpertInformationMapper expertInformationMapper;
    @Autowired
    private FileUploadUtil fileUploadUtil;
    @Value("${user.default.visitpath}")
    private String visitpath;

    /**
     * 获取专家列表
     *
     * @return
     */
    @RequestMapping("listExpertInformation")
    public Message listExpertInformation() {
        return Message.success("获取数据成功！", expertInformationMapper.listExpertInformation());
    }

    /**
     * 根据专家id获取专家信息
     *
     * @param id
     * @return
     */
    @RequestMapping("getExpertInformationById")
    public Message getExpertInformationById(Integer id) {
        return Message.success("获取成功！", expertInformationMapper.selectByPrimaryKey(id));
    }

    /**
     * 编辑专家信息
     *
     * @param expertInformation
     * @return
     */
    @RequestMapping("editExpertInformation")
    public Message insertExpertInformation(ExpertInformation expertInformation, @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file != null) {
            String headImgUrl = fileUploadUtil.upload(file, "expertInformation");
            expertInformation.setHeadImgUrl(visitpath + headImgUrl);
        }
        if (expertInformation.getId() != null) {
            expertInformationMapper.updateByPrimaryKeySelective(expertInformation);
        } else {
            expertInformationMapper.insert(expertInformation);
        }
        return Message.success("编辑成功！");
    }

    /**
     * 删除专家信息
     *
     * @param ids
     * @return
     */
    @RequestMapping("deleteExpertInformation")
    public Message deleteExpertInformation(String ids) {
        List<String> param = Arrays.asList(ids.split(","));
        return Message.success("删除成功！", expertInformationMapper.deleteExpertInformation(param));
    }

    /**
     * 修改专家信息
     *
     * @param expertInformation
     * @return
     */
    @RequestMapping("updateExpertInformation")
    public Message updateExpertInformation(ExpertInformation expertInformation) {
        return Message.success("修改成功！", expertInformationMapper.updateByPrimaryKeySelective(expertInformation));
    }

}
