package com.siupay.openapi.controller.vo.response;

import java.util.Date;

import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sucre
 * @date 2021年12月08日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("提现流水记录响应")
public class BankTransferPayOutRecordResponse extends BaseDto {

    /**
     * Payout订单id,前缀pot_
     */
    @ApiModelProperty("Payout订单id")
    private String payoutTrxnId;

    /**
     * 外部用户ID(ucenter_id)
     */
    @ApiModelProperty
    private String externalUserId;

    /**
     * 商户用户id
     */
    private String paymentUserId;

    /**
     * 提现账户id
     */
    private String payoutAccountId;

    /**
     * 上游请求单号
     */
    private String requestId;

    /**
     * 上游类型
     */
    private String requestSystem;

    /**
     * 提现通道
     */
    private String channelId;

    /**
     * 提现方式
     */
    private String payoutMethod;

    /**
     * 原始提现金额
     */
    private Long originalAmount;

    /**
     * 货币种类
     */
    private String originalCurrency;

    /**
     * 提现金额
     */
    private Long withdrawAmount;

    /**
     * 货币种类
     */
    private String withdrawCurrency;

    /**
     * payout状态
     */
    private String status;

    /**
     * 错误码
     */
    private String statusCode;

    /**
     * 状态描述
     */
    private String statusMessage;

    /**
     * 渠道返回的单号
     */
    private String channelTrackId;

    /**
     * 请求来源web/ios/andriod或外部系统
     */
    private String source;

    /**
     * 查询次次数
     */
    private Integer queryCount;

    /**
     * 创建时间
     */
    private Date created;

    /**
     * 更新日期
     */
    private Date updated;

    /**
     * 渠道后续处理依赖的上下文信息
     */
    private String channelExtra;

    /**
     * 费用详情
     */
    private String fee;

    /**
     * 平台处理上下文信息
     */
    private String platformExtra;
}
