package com.siupay.openapi.integration.feeEngine;

import com.siupay.openapi.bo.CalculateFeeRequestBo;
import com.siupay.openapi.bo.CalculateFeeResponseBo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeeEngineClient {

    public CalculateFeeResponseBo calculateFee(CalculateFeeRequestBo requestBo) {
        return new CalculateFeeResponseBo();
    }
}
