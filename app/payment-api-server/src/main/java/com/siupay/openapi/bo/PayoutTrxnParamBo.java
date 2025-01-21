package com.siupay.openapi.bo;

import java.math.BigDecimal;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author minn
 * @description
 * @date 2021/11/30
 */
@Data
public class PayoutTrxnParamBo {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 渠道id
     */
    private String channelId;

    /**
     * 支付方式名称
     */
    private String paymentName;

    /**
     * 法币币种
     */
    private String currency;

    /**
     * 提现金额
     */
    private BigDecimal amount;

    /**
     * 收款账户id
     */
    private String payoutAccountId;

    /**
     * 来源
     */
    private String source;

    /**
     * 设备指纹
     */
    private String fingerId;

    /**
     * 扩展参数
     */
    private Map<String, Object> extra;
}
