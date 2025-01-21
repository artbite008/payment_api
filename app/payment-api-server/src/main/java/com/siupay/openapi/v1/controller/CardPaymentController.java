package com.siupay.openapi.v1.controller;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.openapi.v1.service.CardFacadeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/*
temp solution for demo
 */
@RestController
@Api(value = "信用卡一次性支付服务")
@Slf4j
@CrossOrigin
public class CardPaymentController {

    @Autowired
    private CardFacadeService cardService;

    @PostMapping(value = {"/pmtapi/v1/card/payment"})
    public GenericResponse<Map> payment(@RequestBody Object request) {
        Map resultMap = new HashMap();
        resultMap.put("status","Authorized");
        resultMap.put("responseCode","10000");
        resultMap.put("responseSummary","Approved");
        return GenericResponse.success(resultMap);
    }

}
