package com.siupay.openapi.v1.constants;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ItemCode {
    /**
     * cybs url
     */
    public static final String RISK_CYBS_URL = "cybs_url";
    /**
     * merchant_id
     */
    public static final String MERCHANT_ID = "merchant_id";

    /**
     * commonItem
     */
    public static final String COMMON_ITEM = "common_item";

    /**
     * commonItem
     */
    public static final String SESSION_REJECT = "session_reject";

    /**
     * 私钥
     */
    public static final String RSA_PRIVATE = "rsa_private";

    /**
     * rsa加密是否开启
     */
    public static final String RSA_OPEN_STATUS = "rsa_open_status";
    /**
     * 风控是否开启
     */
    public static final String RISK_ENABLED = "risk_enabled";
    /**
     * 公钥
     */
    public static final String RSA_PUBLIC = "rsa_public";
}
