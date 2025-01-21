package com.siupay.openapi.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author uther.chen
 * @description: 订单充值列表
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistoryResponseBo {
    /**
     * 订单ID
     */
    private String orderId;

    /**
     * value = "币种",
     * example = "CNY/USD"
     */
    private String coin;

    /**
     * 交易数量
     */
    private BigDecimal amount;

    /**
     * 订单状态
     * 0/1/2 (进行中/充值成功/充值失败)
     */
    private Integer status;

    /**
     * 申诉状态
     * 0/1/2 (未申诉/申诉中/申诉结束)
     */
    private Integer appealStatus;

    /**
     * 渠道类型
     */
    private String channelType;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 手续费总额
     */
    private BigDecimal totalFee;

    /**
     * 订单创建时间
     */
    private Date createTime;
}
