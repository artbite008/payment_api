package com.siupay.openapi.constant;

import lombok.experimental.UtilityClass;

/**
 * @program: payment-api
 * @description: constant
 * @author: Sandy
 **/
@UtilityClass
public class Constant {

    /**
     * wallet pay in switch
     */
    public static final String WALLET_PAY_IN_SWITCH = "WALLET_PAY_IN_SWITCH";

    public static final String ACCOUNT_NORMAL_RULE = "^.{4,40}";

    public static final String ACCOUNT_STANDARD_RULE = "[a-zA-Z0-9]{4,40}";

    public static final String URL_LIMIT_GLOBAL = "GLOBAL";
}
