package com.jhg.marketing.dao.mapper;

import com.jhg.marketing.dao.domin.WeChatPayRefundApply;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface WeChatPayRefundApplyMapper extends Mapper<WeChatPayRefundApply> {

    @Select({
            "<script>" +
                    "SELECT wpra.*, su.username name ,wp.total_fee totalFee " +
                    "FROM wechat_pay wp, wechat_pay_refund_apply wpra " +
                    "LEFT JOIN sys_user su ON wpra.user_id = su.id " +
                    "WHERE wpra.out_trade_no = wp.out_trade_no " +
                    "<if test=\"status != null\">" +
                    "AND wpra.status = #{status} " +
                    "</if>" +
            "</script>"
    })
    List<WeChatPayRefundApply> listRefundApply(WeChatPayRefundApply weChatPayRefundApply);

    @Select({
            "SELECT TOP 1 wpra.*, wpr.refund_desc refundDesc " +
                    "FROM wechat_pay_refund_apply wpra, wechat_pay_refund wpr " +
                    "WHERE wpra.id = wpr.apply_id " +
                    "AND wpr.refund_fee = #{weChatPayRefundApply.fee} " +
                    "AND wpra.out_trade_no = #{weChatPayRefundApply.outTradeNo} " +
                    "AND wpra.status = #{weChatPayRefundApply.status} " +
                    "ORDER BY wpr.refund_time DESC"
    })
    WeChatPayRefundApply getRefundInfo(WeChatPayRefundApply weChatPayRefundApply);
}
