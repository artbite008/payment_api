package com.siupay.openapi.v1.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Map;

@Data
@ApiModel("风控验证信息")
public class RiskCardRequest {

    @ApiModelProperty("充值币种")
    @NotBlank(message = "currency can't be null")
    private String currency;

    @ApiModelProperty(value = "充值数量")
    @NotBlank(message = "The number of currency can't be null")
    private BigDecimal totalAmount;

    @ApiModelProperty(value = "来源设备", example = "ANDROID,IOS,WEB,M_SITE")
//    @In(value = {"ANDROID", "IOS", "WEB", "M_SITE"})
    private String clientFrom = "WEB";

    @ApiModelProperty("sessionId")
    @NotBlank(message = "sessionId can't be null")
    private String sessionId;

    @ApiModelProperty("卡id")
    @NotBlank(message = "cardId can't be null")
    private String cardId;

    @ApiModelProperty("业务类型, 1.recharge 充值 \n 2. bycrypto 买币")
    @NotBlank(message = "cardId can't be null")
    private String bizType;

    @ApiModelProperty(value = "数字货币")
    private String cryptoCurrency;

    @ApiModelProperty("扩展参数")
    private Map<String, Object> ext;
}
