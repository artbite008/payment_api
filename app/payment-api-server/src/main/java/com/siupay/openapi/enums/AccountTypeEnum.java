package com.siupay.openapi.enums;

import com.siupay.common.lib.enums.ChannelEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author minn
 * @description
 * @date 2022/4/2
 */
@Getter
@AllArgsConstructor
public enum AccountTypeEnum {

    /**
     * thirdPay
     */
    THIRD_PAY("THIRDPAY"),

    /**
     * bank transfer capitual
     */
    CAPITUAL(ChannelEnum.CAPITUAL.getChannelId()),
    ;

    /**
     * 账户类型code
     */
    private String code;


}
