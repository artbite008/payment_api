package com.siupay.openapi.v1.constants;

/**
 * @program: deposit
 * @description: 字典
 * @author: Sandy
 * @create: 2021-06-14 22:15
 **/
public class DictCode {
    private DictCode() {}

    /* 法币过滤dict **/
    public static final String FILTER_FIAT = "filter_fiat";
    /** 法币支持dict */
    public static final String FIAT = "fiat_supported";
    /** 渠道dict */
    public static final String CHANNEL = "channel";
    /** 支付方式返回 */
    public static final String PAYMENT = "payment";
    /**
     * 支付方式scheme图片地址
     */
    public static final String SCHEME_URL = "scheme_url";

    public static final String RISK_INFO = "risk_info";

    public static final String COMMON_PARAM = "common_param";

    public static final String RSA_KEY = "RSA_KEY";

    static class ItemCode {
        private ItemCode() {}

        /** 渠道类型 */
        public static final String TYPE = "type";
        /** 渠道图标 */
        public static final String ICON = "icon";
        /** 渠道手续费类型 */
        public static final String SIUPAY_RATE_TYPE = "siupay_rate_type";
        /** 手续费类型 */
        public static final String SIUPAY_FEE = "siupay_fee";
        /**
         * VISA
         */
        public static final String VISA_URL = "visa_url";

    }

}
