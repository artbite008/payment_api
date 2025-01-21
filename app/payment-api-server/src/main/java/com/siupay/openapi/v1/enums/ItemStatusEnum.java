package com.siupay.openapi.v1.enums;

import lombok.Getter;

/**
 * @program: deposit
 * @description:
 * @author: Sandy
 **/
@Getter
public enum ItemStatusEnum {
    /**
     * 字典开启状态
     */
    OPEN_STATUS(1, "开启状态"),

    /**
     * 字典关闭状态
     */
    CLOSE_STATUS(0, "关闭状态");

    private Integer status;
    private String desc;

    ItemStatusEnum(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
