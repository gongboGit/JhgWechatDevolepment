package com.jhg.marketing.dao.domin;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lxl
 * @since 2019/4/8 9:03
 */
@Data
@Table(name = "wechat_pay_refund")
public class WeChatPayRefund {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    /**
     * 商户订单号
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    @Column(name = "out_refund_no")
    private String outRefundNo;

    /**
     * 商户退款金额，单位：分
     */
    @Column(name = "refund_fee")
    private BigDecimal refundFee;

    /**
     * 商户退款货币种类
     */
    @Column(name = "refund_fee_type")
    private String refundFeeType;

    /**
     * 商户退款原因
     */
    @Column(name = "refund_desc")
    private String refundDesc;

    /**
     * 商户退款资金来源
     */
    @Column(name = "refund_account")
    private String refundAccount;

    /**
     * 退款时间
     */
    @Column(name = "refund_time")
    private Date refundTime;

    /**
     * 申请Id
     */
    @Column(name = "apply_id")
    private Integer applyId;

    @Transient
    private BigDecimal totalFee;

    @Transient
    private Integer status;
}
