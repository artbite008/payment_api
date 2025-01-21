package com.siupay.openapi.bo;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class PlatformAccountInfoResultBo {

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
    private String displayName;

    /**
     * 用户账户子类型
     */
    private String accountTag;

    /**
     * 币种
     */
    private String currency;

    /**
     * 账户总额
     */
    private BigDecimal totalBalance;

    /**
     * 账户可用余额
     */
    private BigDecimal availableBalance;

    /**
     * 账户冻结金额
     */
    private BigDecimal holdBalance;

    /**
     * 更新时间
     */
    private Date updatedAt;


}
