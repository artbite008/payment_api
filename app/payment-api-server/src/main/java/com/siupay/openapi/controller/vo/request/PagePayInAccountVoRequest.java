package com.siupay.openapi.controller.vo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagePayInAccountVoRequest {

    private String userId;
    private String iban;
    private String reference;
    private String channelId;
    private int pageNum;
    private int pageSize;
}
