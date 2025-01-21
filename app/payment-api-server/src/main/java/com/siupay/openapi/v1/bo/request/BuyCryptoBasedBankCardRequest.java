package com.siupay.openapi.v1.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel("银行卡买币入参")
public class BuyCryptoBasedBankCardRequest {

    /**
     * 法币
     */
    @ApiModelProperty(value = "法币", example = "USD")
    @NotEmpty(message = "fiat is empty!")
    private String fiatCurrency;

    /**
     * 数字货币
     */
    @ApiModelProperty(value = "数字货币", example = "BTC")
    @NotEmpty(message = "crypto is empty!")
    private String cryptoCurrency;

    /**
     * 法币交易金额
     */
    @ApiModelProperty(value = "法币金额", example = "USD")
    @NotNull(message = "fiatAmount is null")
    @Min(value = 0, message = "recharge amount less than 0")
    @Max(value = 1000000000, message = "rechargeAmount more than 1000000000")
    private BigDecimal fiatAmount;

    /**
     * 数字货币交易数量
     */
    @ApiModelProperty(value = "数字货币金额", example = "BTC")
    private BigDecimal cryptoQuantity;

    /**
     * 渠道名称
     */
    @ApiModelProperty(value = "渠道名称", hidden = true)
    private String channelName = "checkout";

    /**
     * 渠道类型
     */
    @ApiModelProperty(value = "渠道类型")
    @NotEmpty(message = "channelType is empty!")
    private String channelType;

    /**
     * 订单来源设备
     */
    @ApiModelProperty(value = "订单来源设备", example = "ANDROID,IOS,WEB,M_SITE")
//    @In(value = {"ANDROID", "IOS", "WEB", "M_SITE"})
    private String clientFrom = "WEB";

    /**
     * 扩展属性
     */
    @ApiModelProperty(value = "扩展属性", example = "CHECKOUT-CARD-ID")
    private Map<String, Object> ext = new HashMap<>();
}
