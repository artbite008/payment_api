package com.siupay.openapi.controller.vo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaymentAmountResponse {

    @JsonProperty("amount")
    private String amount;
    @JsonProperty("currency")
    private String currency;
}
