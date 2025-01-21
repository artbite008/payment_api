package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserChannelInfoVoResponse extends BaseDto {

    @ApiModelProperty(value = "账户验证状态")
    @JsonProperty(value = "verify_status")
    private String verifyStatus;
}
