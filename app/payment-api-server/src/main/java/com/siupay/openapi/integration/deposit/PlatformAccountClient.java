package com.siupay.openapi.integration.deposit;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.openapi.controller.vo.request.PlatformAccountInfoQueryRequest;
import com.siupay.openapi.controller.vo.response.PlatformAccountInfoResponse;
import com.siupay.openapi.controller.vo.response.PlatformAccountSummaryInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author minn
 * @description
 * @date 2022/4/2
 */
@Component
@Slf4j
public class PlatformAccountClient {

    public PlatformAccountSummaryInfoResponse queryAccountSummaryInfo() {
        try {
            GenericResponse<PlatformAccountSummaryInfoResponse> response =
                    GenericResponse.success(new PlatformAccountSummaryInfoResponse());
            if (null != response && response.isSuccess()) {
                return response.getData();
            }
            log.error("checkout queryAccountSummaryInfo error, response:[{}]", response);
        } catch (Exception e) {
            log.error("checkout queryAccountSummaryInfo error", e);
        }
        return null;
    }

    public List<PlatformAccountInfoResponse> queryAccounts(PlatformAccountInfoQueryRequest request) {
        try {
            GenericResponse<List<PlatformAccountInfoResponse>> response = GenericResponse.success();
            if (null != response && response.isSuccess()) {
                return response.getData();
            }
            log.error("checkout queryAccounts error, response:[{}]", response);
        } catch (Exception e) {
            log.error("checkout queryAccounts error", e);
        }
        return null;
    }



}
