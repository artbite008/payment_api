package com.siupay.openapi.controller.vo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Sucre
 * @date 2021年12月08日
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("分页查询提现交易流水")
public class PayOutTransactionQueryRequest extends BaseDto {

    /**
     * 当前页
     */
    @ApiModelProperty("当前页")
    @JsonProperty("current_page")
    private Integer currentPage;

    /**
     * 分页大小
     */
    @ApiModelProperty("分页大小")
    @JsonProperty("page_size")
    private Integer pageSize = 10;
}
