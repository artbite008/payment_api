package com.siupay.openapi.v1.enums;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * 支付方式枚举
 * 
 * @author Sucre
 * @date 2021年07月26日
 */
@Getter
@AllArgsConstructor
public enum PaymentMethodEnum {

    /**
     * 信用卡买币
     */
    BANK_CARD(2, "银行卡", Lists.newArrayList("expireDateFilter", "cardInfoFilter"), Boolean.TRUE),

    /**
     * 余额
     */
    BALANCE(1, "余额", Lists.newArrayList("expireDateFilter"), Boolean.FALSE),

    /**
     * sepa
     */
    SEPA_TRANSACTIVE(3, "sepa", Lists.newArrayList("expireDateFilter"), Boolean.FALSE);

    /**
     * 排序值
     */
    private int sort;

    /**
     * 描述
     */
    private String desc;

    /**
     * 算费过滤器
     */
    private List<String> feeCalculateFilers;

    /**
     * 是否需要银行卡
     */
    private Boolean needCard;

    /**
     * 通过name转换
     *
     * @param name
     * @return
     */
    public static PaymentMethodEnum convertByName(String name) {
        return Arrays.stream(PaymentMethodEnum.values())
                .filter(item -> item.name().equalsIgnoreCase(name))
                .findAny()
                .orElse(null);
    }
}
