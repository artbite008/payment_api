package com.siupay.openapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LimitLockKeyEnum {
    //admin
    RENEW("AdminBankTransferPayInController.renew"),
    VALIDATE("AdminBankTransferPayInController.validate"),
    REFUNDED("AdminBankTransferPayInController.refunded"),
    TRANSACTIONS("AdminBankTransferQueryController.transactions"),
    PAYINACCOUNTS("AdminPayInAccountController.payinAccounts"),
    //用户
    USER("User.global")
    ;
    String value;
}
