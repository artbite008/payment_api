package com.siupay.openapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author minn
 * @description
 * @date 2022/4/22
 */
@Getter
@AllArgsConstructor
public enum ExternalPayoutStatusEnum {

    CREATED("created", "pending", "创建成功"),

    PENDING_ACCOUNT("pending_account", "pending", "资金已冻结"),

    PENDING_RISK("pending_risk", "review", "风控审核中"),

    PENDING_CHANNEL("pending_channel", "pending", "请求渠道出款中"),

    REJECTED("rejected", "rejected", "已拒绝"),

    DEFAULT("default", "pending", "默认处理中");

    /**
     * 内部code
     */
    private String internalCode;

    /**
     * 对外code
     */
    private String externalCode;

    /**
     * 描述
     */
    private String desc;

    public static ExternalPayoutStatusEnum getByInternalCode(String internalCode) {
        for (ExternalPayoutStatusEnum statusEnum : ExternalPayoutStatusEnum.values()) {
            if (statusEnum.getInternalCode().equals(internalCode)) {
                return statusEnum;
            }
        }
        return DEFAULT;
    }

}
