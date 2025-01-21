package com.siupay.openapi.bo;

import lombok.Data;

import java.util.Date;

/**
 * @author minn
 * @description
 * @date 2021/11/28
 */
@Data
public class RiskPayoutAccountResultBo {
    /**
     * 用户ip
     */
    private String ip;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 网关用户id
     */
    private String paymentUserId;

    /**
     * 收款账户id
     */
    private String payoutAccountId;

    /**
     * 渠道id
     */
    private String channelId;

    /**
     * 法币币种
     */
    private String currency;

    /**
     * 卡片别名
     */
    private String discription;

    /**
     * 收款账户名称
     */
    private String accountName;

    /**
     * 账户编号
     */
    private String iban;

    /**
     * 账户尾号
     */
    private String ibanLast4;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行路由编号
     */
    private String bic;

    /**
     * 收款人地址
     */
    private String payeeAddress;

    /**
     * 分支行名称
     */
    private String branchName;

    /**
     * 中间行名称
     */
    private String intermediaryName;

    /**
     * 中间行路由编号
     */
    private String intermediarySwiftCode;

    /**
     * 中间行地址
     */
    private String intermediaryAddress;

    /**
     * 账户状态
     */
    private String status;
    /**
     * 操作时间
     */
    private String operateTime;
    /**
     * 数据来源
     */
    private String source;

}
