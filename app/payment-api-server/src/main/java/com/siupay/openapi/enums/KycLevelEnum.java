package com.siupay.openapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author minn
 * @description
 * @date 2021/11/29
 */
@Getter
@AllArgsConstructor
public enum KycLevelEnum {

    NO_KYC("NO_KYC", -1), LEVEL_ONE("LEVEL_ONE", 1), LEVEL_TWO("LEVEL_TWO", 2);

    private String name;

    private Integer value;

}
