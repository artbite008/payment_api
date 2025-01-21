package com.siupay.openapi.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author minn
 * @description
 * @date 2021/11/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PayoutAccountQueryParamBo extends BasePageBo {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 清算网络
     */
    private String channelId;

    /**
     * 法币币种
     */
    private String currency;

    /**
     * 提现账户id
     */
    private String paymentAccountId;

}
