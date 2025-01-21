package com.siupay.openapi.v1.controller;


import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.openapi.annotation.AccessLimit;
import com.siupay.openapi.enums.LimitLockKeyEnum;
import com.siupay.openapi.enums.LimitTypeEnum;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

@RestController
@Slf4j
@CrossOrigin
public class PaymentMethodController {

    @ApiOperation("查询对应法币下支持的充值渠道")
    @ApiImplicitParam(value = "fiat", name = "法币")
    @GetMapping(value = {"/pmtapi/v1/channels"})
    public GenericResponse<Response> supportRechargeChannel(
            @RequestParam("fiat") @NotEmpty(message = "fiat is not allowed to empty!") String fiat) {
        return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),
                ErrorCode.SERVER_ERROR.getMsg());
    }

    @ApiOperation("查询快捷买币渠道列表")
    //quote接口限流避免用户爬虫频繁访问,JIRA:PA-2537
    @AccessLimit(lockKey = LimitLockKeyEnum.USER, limitType = LimitTypeEnum.USER)
    @GetMapping(value = {"/pmtapi/v1/quotes"})
    public GenericResponse<Response> quotes(
    ) {
        return GenericResponse.fail(ErrorCode.SERVER_ERROR.getCode(),
                ErrorCode.SERVER_ERROR.getMsg());

    }

}
