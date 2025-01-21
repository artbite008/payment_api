package com.siupay.openapi.bo;

import com.siupay.openapi.controller.vo.response.RechargeChannelResponse;
import com.siupay.openapi.v1.bo.response.ChannelCardInfoResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: deposit
 * @description: 支付方式返回
 * @author: Sandy
 * @create: 2021-07-13 20:47
 **/
@Data
@ApiModel(value = "法币支付方式返回")
public class PaymentChannelBo {

    /**
     * 支付方式code
     */
    @ApiModelProperty(value = "支付方式名称", example = "bank_card")
    private String paymentCode;

    /**
     * 支付方式名称
     */
    @ApiModelProperty(value = "支付方式名称", example = "bank card")
    private String paymentName;

    /**
     * 支付方式绑定卡片
     */
    @ApiModelProperty(value = "支付方式绑定卡片详细信息")
    private ChannelCardInfoResponse channelCardInfoResponse;

    /**
     * 支付方式图标
     */
    @ApiModelProperty(value = "支付方式图标")
    private String paymentIcon;

    /**
     * 支付方式统一手续费率
     */
    @ApiModelProperty(value = "支付方式统一手续费", example = "3%")
    private BigDecimal paymentFee;

    /**
     * 固定手续费
     */
    @ApiModelProperty(value = "固定手续费", example = "1")
    private BigDecimal fixedFee;

    /**
     * 最小手续费
     */
    @ApiModelProperty(value = "最小手续费", example = "10")
    private BigDecimal minFee;

    /**
     * 最小法币限额
     */
    @ApiModelProperty(value = "法币最小限额", example = "10")
    private BigDecimal minFiatAmount;

    /**
     * 最大法币限额
     */
    @ApiModelProperty(value = "法币最大限额", example = "100000")
    private BigDecimal maxFiatAmount;

    /**
     * 最大手续费
     */
    @ApiModelProperty(value = "最大手续费", example = "10")
    private BigDecimal maxFee;

    /**
     * 手续费展示字段
     */
    @ApiModelProperty(value = "手续费展示字段", example = "3%+1 USD")
    private String feeMsg;

    /**
     * 支付方式tag(用于支付方式活动文案提示)
     */
    @ApiModelProperty(value = "支付方式名称", example = "Hot!")
    private String tag;

    /**
     * 支付方式开启状态
     */
    @ApiModelProperty(value = "支付方式开启状态", example = "0:关闭, 1:开启")
    private Integer paymentStatus;

    /**
     * 支付方式提示
     */
    @ApiModelProperty(value = "支付方式提示", example = "维护/渠道不支持")
    private String paymentMessage;

    @ApiModelProperty("渠道id")
    private String channelId;

    /**
     * 支付方式下支持的渠道
     */
    @ApiModelProperty(value = "支付方式下支持的渠道")
    private List<RechargeChannelResponse> channelList;

    /**
     * 路由排序
     */
    @ApiModelProperty(value = "支付方式名称")
    private Integer sort;

    /**
     * 是否允许使用
     */
    @ApiModelProperty(value = "是否允许使用")
    private Boolean isAllowed=true;
}
