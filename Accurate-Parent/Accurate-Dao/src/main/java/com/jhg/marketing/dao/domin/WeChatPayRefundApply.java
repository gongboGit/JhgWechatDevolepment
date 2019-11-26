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
@Table(name = "wechat_pay_refund_apply")
public class WeChatPayRefundApply {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    /**
     * 商户订单号
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 申请状态：1.申请中，2.已退款，3.退款失败，0，申请失败
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 申请时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 申请退款金额
     */
    @Column(name = "fee")
    private BigDecimal fee;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 操作人
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 操作人
     */
    @Column(name = "update_time")
    private Date updateTime;

    @Transient
    private String name;

    @Transient
    private String totalFee;

    @Transient
    private String refundDesc;

}
