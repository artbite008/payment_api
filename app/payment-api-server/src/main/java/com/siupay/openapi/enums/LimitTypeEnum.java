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
public enum LimitTypeEnum {

    USER("user", "payment:api:limit:user:"), ADMIN("admin", "payment:api:limit:admin:");

    String name;

    String keyPre;

}
