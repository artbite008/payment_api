package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

/**
 * @author minn
 * @description
 * @date 2022/5/25
 */
@Builder
@Getter
public class UserKycInfoResponse extends BaseDto {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "kyc姓名")
    @JsonProperty(value = "kyc_name")
    private String kycName;

}
