package com.siupay.openapi.bo;

import lombok.Data;

@Data
public class PlatformAccountBaseInfoResultBo {

    /**
     * 账户编号
     */
    private String accountId;

    /**
     * 账户类型
     */
    private String accountType;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 用户账户子类型
     */
    private String accountTag;

}
