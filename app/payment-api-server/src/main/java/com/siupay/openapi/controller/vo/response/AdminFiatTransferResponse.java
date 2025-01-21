package com.siupay.openapi.controller.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Ken.Liu
 * @description
 * @date 2022/5/16
 */
@Data
@ApiModel("账户资金划转响应")
public class AdminFiatTransferResponse {

    @ApiModelProperty("状态（SUCCEEDED、FAILED）")
    private String status;

    @ApiModelProperty("错误信息")
    private String errorMsg;

    @ApiModelProperty("账务系统业务id")
    private String accountBizNo;

    @ApiModelProperty("账务系统订单id")
    private String accountOrderId;
}


