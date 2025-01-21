package com.siupay.openapi.bo;

import lombok.Data;

import java.util.Map;

/**
 * @author minn
 * @description
 * @date 2021/11/28
 */
@Data
public class PayoutAccountDelParamBo {
    /**
     * 用户id
     */
    private String userId;

    private String payoutAccountId;

    @Deprecated
    private String fingerId;

    /**
     * 设备来源
     */
    private String clientFrom;

    /**
     * 账户渠道附加信息
     */
    private Map<String, Object> extra;

}
