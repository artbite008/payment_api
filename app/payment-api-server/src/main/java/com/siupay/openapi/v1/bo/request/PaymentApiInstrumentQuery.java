package com.siupay.openapi.v1.bo.request;

import lombok.Data;

@Data
public class PaymentApiInstrumentQuery {
    private String uid;
    private String email;
    private String phone; // TODO
}
