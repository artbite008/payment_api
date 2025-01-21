package com.siupay.openapi.bo;

import lombok.Data;

@Data
public class PlatformAccountQueryParamBo {

    /**
     * 交易类型
     */
    private String accountType;

    /**
     * 交易账号
     */
    private String accountId;

    /**
     * 交易币种
     */
    private String currency;
}
