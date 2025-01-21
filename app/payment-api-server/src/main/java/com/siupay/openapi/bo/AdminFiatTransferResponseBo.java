package com.siupay.openapi.bo;

import lombok.Data;

@Data
public class AdminFiatTransferResponseBo {

    private String status;

    private String errorMsg;

    private String accountBizNo;

    private String accountOrderId;
}



