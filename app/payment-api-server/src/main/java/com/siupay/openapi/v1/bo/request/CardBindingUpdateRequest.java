package com.siupay.openapi.v1.bo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("卡更新实体")
public class CardBindingUpdateRequest {

    @ApiModelProperty("卡id")
    @NotBlank(message = "status can't be null;")
    private String cardId;

    @ApiModelProperty("绑定状态:1绑定,0解绑\n删除操作:0删除")
    private Integer status;

    @ApiModelProperty("支付方式")
    @NotBlank(message = "status can't be null")
    private String channelType;

}

