package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: ywainlan
 * @date: 2022/5/1
 * @Description:
 */
@ApiModel("ContextVo")
@Data
public class ContextVo {

    @JsonProperty(value = "index")
    @ApiModelProperty(value = "偏移量")
    private Integer index;

}
