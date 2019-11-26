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
@Table(name = "wechat_pay")
public class WeChatPay {

    @Id
    @Column(name = "id", insertable = false, updatable = false)
    private Integer id;

    /**
     * 货币类型
     */
    @Column(name = "fee_type")
    private String feeType;

    /**
     * 商户号
     */
    @Column(name = "mch_id")
    private String mchId;

    /**
     * 商户订单号
     */
    @Column(name = "out_trade_no")
    private String outTradeNo;

    /**
     * 商户订单号
     */
    @Column(name = "transaction_id")
    private String transactionId;

    /**
     * 签名
     */
    @Column(name = "sign")
    private String sign;

    /**
     * 支付金额，单位：分
     */
    @Column(name = "total_fee")
    private BigDecimal totalFee;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 交易状态
     * 0:SUCCESS—支付成功
     * 1:REFUND—转入退款
     * 2:NOTPAY—未支付
     * 3:CLOSED—已关闭
     * 4:REVOKED—已撤销（付款码支付）
     * 5:USERPAYING--用户支付中（付款码支付）
     * 6:PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    @Column(name = "trade_state")
    private Integer tradeState;

    /**
     * 支付用户openId
     */
    @Column(name = "open_id")
    private String openId;

    /**
     * 预支付标识
     */
    @Column(name = "prepay_id")
    private String prepayId;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * his数据
     */
    @Column(name = "data")
    private String data;

    /**
     * 下单人Id
     */
    @Column(name = "wechat_user_id")
    private Integer weChatUserId;

    /**
     * 挂号时间
     */
    @Column(name = "register_time")
    private Date registerTime;

    /**
     * 下单人姓名
     */
    @Transient
    private String name;

    /**
     * 下单人身份证号
     */
    @Transient
    private String idCard;

}
