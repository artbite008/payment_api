package com.siupay.openapi.bo;

import java.util.Map;

import lombok.Data;

/**
 * @author minn
 * @description
 * @date 2021/11/28
 */
@Data
public class PayoutAccountAddParamBo {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 渠道id
     */
    private String channelId;

    /**
     * 法币币种
     */
    private String currency;

    /**
     * 账号名称
     */
    private String accountName;

    /**
     * 账户编号
     */
    private String iban;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行路由编号
     */
    private String bic;

    /**
     * 卡片别名
     */
    private String discription;
    /**
     * 设备来源
     */
    private String clientFrom;

    /**
     * 邮箱
     */
    private String payeeEmail;

    /**
     * 账户渠道附加信息
     */
    private Map<String, Object> extra;

    /**
     * 设备指纹
     */
    private String fingerId;

}
