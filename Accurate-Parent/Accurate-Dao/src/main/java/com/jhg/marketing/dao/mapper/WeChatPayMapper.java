package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.WeChatPay;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface WeChatPayMapper extends Mapper<WeChatPay> {

    @Select({
            "SELECT wp.*" +
                    "FROM wechat_pay wp "
    })
    List<WeChatPay> listOrder(WeChatPay weChatPay);

    @Select({
            "SELECT wp.*,wu.id_card idCard,wu.true_name name " +
                    "FROM wechat_pay wp,wechat_user wu " +
                    "WHERE wp.wechat_user_id = wu.id " +
                    "AND register_time IS NOT NULL " +
                    "ORDER BY register_time DESC"
    })
    List<WeChatPay> listWeChatPayForRegister(WeChatPay weChatPay);
}
