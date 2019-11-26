package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.WeChatTag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


public interface WeChatTagMapper extends Mapper<WeChatTag> {

    @Insert({
            "<script>" +
            "INSERT INTO wechat_tag(id,name) " +
                    "VALUES " +
                    "<foreach collection=\"list\" separator=\",\" item=\"item\">" +
                    "(#{item.id},#{item.name})"+
                    "</foreach>" +
            "</script>"
    })
    @Options(useGeneratedKeys = false)
    Integer batchInsert(@Param("list") List<Map> list);

    @Update({
            "TRUNCATE TABLE wechat_tag"
    })
    @Options(useGeneratedKeys = false)
    Integer truncate();
}
