package com.siupay.openapi.constant;

import lombok.Getter;

public class PayInAccountConstant {

    public static final String DEFAULT_TITLE_TXT = "title";
    public static final String DEFAULT_SORT_TXT = "sort";
    public static final String DEFAULT_CHANNEL_ID_TXT = "channel_id";
    public static final String PAYIN_ACCOUNT_KYCNAME="kycName";

    @Getter
    public enum PayInAccountElementEnum {
        COMPANY_NAME("companyName", "Account Name", 2, "公司名"),
        COMPANY_ADDRESS("companyAddress", "Address", 3, "公司地址"),
        BANK_NAME("bankName", "Bank Name", 3, "银行名称"),
        BANK_ADDRESS("bankAddress", "Bank Address", 4, "银行地址"),
        BIC("bic", "BIC", 6, "银行标识"),
        IBAN("iban", "IBAN", 5, "账号"),
        REFERENCE("reference", "reference", 1, "附言");

        private String code;
        private String tile;
        private int sort;
        private String desc;

        PayInAccountElementEnum(String code, String tile, int sort, String desc) {
            this.code = code;
            this.tile = tile;
            this.sort = sort;
            this.desc = desc;
        }
    }
}
