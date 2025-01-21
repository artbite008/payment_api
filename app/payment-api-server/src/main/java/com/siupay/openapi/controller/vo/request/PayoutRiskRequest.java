package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author gemeng
 * @description
 * @date 2022/02/17
 */
@Data
public class PayoutRiskRequest {

    /**
     * 外部用户ID(ucenter_id)
     */
    @JsonProperty(value = "external_user_id")
    @ApiModelProperty(value = "外部用户ID(ucenter_id)", name = "external_user_id")
    private String externalUserId;
}
