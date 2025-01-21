package com.siupay.openapi.bo;

import com.siupay.common.api.dto.PaymentAmount;
import lombok.Data;

/**
 * @author Ken.Liu
 * @description
 * @date 2022/5/17
 */
@Data
public class AdminFiatTransferRequestBo {
    private PaymentAmount transferAmount;

    private String remark;

    private String recOwnerId;

    private String recAccountType;

    private String recTag;

    private String payOwnerId;

    private String payAccountType;

    private String payTag;
}


