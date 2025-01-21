package com.siupay.openapi.integration;

import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.openapi.controller.vo.request.SupportChannelRequest;
import com.siupay.openapi.controller.vo.response.RechargePaymentResponse;
import com.siupay.openapi.util.utils.BeanConvert;
import com.siupay.openapi.util.utils.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Martim
 * @date 2022年03月13日
 */
@Slf4j
@Component
public class ChannelClient {
    @Autowired
    RestClient restClient;
    /**
     * 获取渠道
     *
     * @return
     */
    public List<RechargePaymentResponse> supportChannel(SupportChannelRequest supportChannelRequest) {
        GenericResponse<List<RechargePaymentResponse>> result =
                restClient.get("/v1/common/channel", BeanConvert.convertToMultiValueMap(supportChannelRequest),
                        new ParameterizedTypeReference<GenericResponse<List<RechargePaymentResponse>>>() {});
        return result.getData();
    }

}
