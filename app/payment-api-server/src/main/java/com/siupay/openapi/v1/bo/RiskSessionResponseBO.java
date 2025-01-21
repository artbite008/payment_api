package com.siupay.openapi.v1.bo;

import lombok.Data;

@Data

public class RiskSessionResponseBO {
    public String sessionId;
    public String cybsUrl;

    public RiskSessionResponseBO(String sessionId, String merchantId, String cybsUrl) {
        setCybsUrl(cybsUrl);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(merchantId).append(sessionId);
        setSessionId(stringBuilder.toString());
    }
}
