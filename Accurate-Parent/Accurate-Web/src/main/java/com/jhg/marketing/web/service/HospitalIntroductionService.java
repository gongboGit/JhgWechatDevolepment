package com.jhg.marketing.web.service;

import com.jhg.marketing.dao.domin.HospitalIntroduction;
import com.jhg.marketing.dao.mapper.HospitalIntroductionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author lxl
 * @since 5/5/2019 9:49 AM
 */
@Service
public class HospitalIntroductionService {
    @Autowired
    private HospitalIntroductionMapper hospitalIntroductionMapper;

    public List<HospitalIntroduction> listHospitalIntroduction() {
        return hospitalIntroductionMapper.listHospitalIntroductionBySort();
    }

    public boolean deleteHospitalIntroduction(String ids) {
        List<String> idList = Arrays.asList(ids.split(","));
        //删除医院介绍
        hospitalIntroductionMapper.deleteByIds(idList);
        return true;
    }

    public HospitalIntroduction getIntroductionInfo(Integer id) {
        return hospitalIntroductionMapper.selectByPrimaryKey(id);
    }

    public boolean insertHospitalIntroduction(HospitalIntroduction hospitalIntroduction) {
        return hospitalIntroductionMapper.insert(hospitalIntroduction) == 1;
    }

    public boolean updateHospitalIntroduction(HospitalIntroduction hospitalIntroduction) {
        return hospitalIntroductionMapper.updateByPrimaryKeySelective(hospitalIntroduction) == 1;
    }
}
