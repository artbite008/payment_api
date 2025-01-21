package com.siupay.openapi.bo;

import java.util.Date;

import lombok.Data;

/**
 * @author minn
 * @description
 * @date 2021/12/1
 */
@Data
public class UserAuthResultBo {

    /**
     * kyc标志
     */
    private boolean isKyc;

    /**
     * kyc姓名
     */
    private String kycName;

    /**
     * kyc所在国家
     */
    private String kycArea;

    /**
     * kyc等级
     */
    private Integer kycLevel;

    /**
     * 是否绑定手机
     */
    private boolean isBindPhone;

    /**
     * 是否设置交易密码
     */
    private boolean isBindTradePassword;

    /**
     * vip等级
     */
    private Integer honorLevel;

    /**
     * 当前会员级别
     */
    private Integer subLevel;

    /**
     * 创建时间
     */
    private Date createdAt;
}
