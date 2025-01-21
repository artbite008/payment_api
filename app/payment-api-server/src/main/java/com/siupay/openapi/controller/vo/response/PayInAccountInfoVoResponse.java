package com.siupay.openapi.controller.vo.response;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.siupay.common.api.dto.BaseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PayInAccountInfoVoResponse extends BaseDto {

    /**
     * 渠道id
     */
    @JsonProperty(value = "channel_id")
    private String channelId;
    /**
     * 渠道认证状态
     */
    @JsonProperty(value = "verify_status")
    private String verifyStatus;
    /**
     * sepa渠道kyc是否通知
     */
    @JsonProperty(value = "sepa_kyc_notice")
    private Boolean sepaKycNotice=Boolean.FALSE;
    /**
     * 支付展示页面信息
     */
    private List<Map<String, Object>> items;

    /**
     * 重定向地址
     */
    @JsonProperty(value = "redirect_url")
    private String redirectUrl;

}
