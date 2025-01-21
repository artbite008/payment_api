package com.siupay.openapi.integration.channelCore;

import com.siupay.common.api.enums.PaymentSystem;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentError;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.openapi.controller.vo.request.PlatformAccountQueryRequest;
import com.siupay.openapi.controller.vo.response.PlatformAccountInfoResponse;
import com.siupay.openapi.controller.vo.response.PlatformAccountSummaryInfoResponse;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author minn
 * @description
 * @date 2022/4/2
 */
@Slf4j
@Component
public class ChannelPlatformAccountClient {


    public PlatformAccountSummaryInfoResponse queryAccountSummaryInfo(String channelId) {
        try {
            Either<PaymentError, PlatformAccountSummaryInfoResponse> ei =
                   Either.right(new PlatformAccountSummaryInfoResponse());
            if (ei.isLeft()) {
                throw new PaymentException(ErrorCode.getErrorCodeByCode(ei.getLeft().getCode()), ei.getLeft().getMsg(),
                        PaymentSystem.PAYMENT_API);
            }
            return ei.get();
        } catch (Exception e) {
            log.error(String.format("[%s] queryAccountSummaryInfo error", channelId), e);
        }
        return null;
    }

    public List<PlatformAccountInfoResponse> queryAccounts(PlatformAccountQueryRequest request) {
        try {
            Either<PaymentError, List<PlatformAccountInfoResponse>> ei =
                    Either.left(new PaymentError());
            if (ei.isLeft()) {
                throw new PaymentException(ErrorCode.getErrorCodeByCode(ei.getLeft().getCode()), ei.getLeft().getMsg(),
                        PaymentSystem.PAYMENT_API);
            }
            return ei.get();
        } catch (Exception e) {
            log.error("queryAccountSummaryInfo error", e);
        }
        return null;
    }
}
