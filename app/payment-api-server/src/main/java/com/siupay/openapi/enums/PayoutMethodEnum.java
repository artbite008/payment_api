package com.siupay.openapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author gemeng
 * @description
 * @date 2022/02/17
 */
@Getter
@AllArgsConstructor
public enum PayoutMethodEnum {

    DEFAULT("00", "default payment method"),
    BANK_TRANSFER("01", "bank transfer"),
    CREDIT_CARD("02", "Credit Card"),
    WALLET("03", "e-Wallet"),
    BANK_CARD("04", "Bank Card"),
    UNKNOWN("99", "Unknown Transaction Type");

    private String code;

    private String name;

}
