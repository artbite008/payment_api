package com.siupay.openapi.bo;

import com.siupay.common.api.dto.PaymentAmount;

import lombok.Data;

@Data
public class PayoutFeeBO {

    /**
     * platform fee
     */
    private PaymentAmount platformFee;
    /**
     * channel transaction fee
     */
    private PaymentAmount channelFee;
    /**
     * channel transaction reverse fee
     */
    private PaymentAmount channelReversedFee;
}
