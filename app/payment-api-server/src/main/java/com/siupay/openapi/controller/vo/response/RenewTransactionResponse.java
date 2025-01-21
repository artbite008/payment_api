package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RenewTransactionResponse extends BaseDto {

    /**
     * 匹配的用户id
     */
    @JsonProperty(value = "user_id")
    @ApiModelProperty(value = "匹配的用户id", name = "user_id")
    private String userId;
    /**
     * 匹配的结果
     */
    @JsonProperty(value = "status")
    @ApiModelProperty(value = "匹配的结果:成功：true，失败：false", name = "status")
    private String status;
}
