package com.siupay.openapi.controller.vo.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@ApiModel
@Data
public class BasePageRequest {

    @Min(value = 1)
    @ApiModelProperty(value = "当前页", example = "1")
    private Integer page = 1;

    @Max(value = 100)
    @Min(value = 1)
    @ApiModelProperty(value = "每页展示数量", example = "10")
    private Integer pageSize = 10;

}
