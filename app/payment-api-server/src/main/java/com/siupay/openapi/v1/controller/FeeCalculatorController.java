package com.siupay.openapi.v1.controller;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.common.lib.utils.BeanUtils;
import com.siupay.openapi.bo.CalculateFeeRequestBo;
import com.siupay.openapi.bo.CalculateFeeResponseBo;
import com.siupay.openapi.controller.vo.request.CalculateFiatFeeRequest;
import com.siupay.openapi.controller.vo.response.CalculateFiatFeeResponse;
import com.siupay.openapi.integration.feeEngine.FeeEngineClient;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Uther.chen
 * @date 2022年04月08日
 */
@Slf4j
@RestController
@CrossOrigin
public class FeeCalculatorController {

    @Autowired
    private FeeEngineClient feeEngineClient;

    /**
     * 法币算费接口
     *
     * @param calculateFiatFeeRequest
     * @return
     */
    @ApiOperation("用户法币算费")
    @PostMapping(value={"/pmtapi/v1/fee/calculate"})
    public GenericResponse<CalculateFiatFeeResponse> calculateCustomerFee(
            @RequestBody @Validated CalculateFiatFeeRequest calculateFiatFeeRequest) {
        CalculateFeeRequestBo calculateCustomerFeeRequestBo =
                BeanUtils.copyProperties(calculateFiatFeeRequest, CalculateFeeRequestBo.class);
        calculateCustomerFeeRequestBo.setChannelId(calculateFiatFeeRequest.getPaymentMethodCode().toUpperCase());
        calculateCustomerFeeRequestBo.setPaymentMethodCode(calculateFiatFeeRequest.getPaymentMethodCode().toUpperCase());

        CalculateFeeResponseBo responseBo = feeEngineClient.calculateFee(calculateCustomerFeeRequestBo);
        return GenericResponse.success(BeanUtils.copyProperties(responseBo, CalculateFiatFeeResponse.class));
    }

}
