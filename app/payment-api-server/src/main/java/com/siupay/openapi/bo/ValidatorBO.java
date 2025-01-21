package com.siupay.openapi.bo;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: payment-api
 * @description: Validator bo
 * @author: Sandy
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidatorBO {
    /**
     * channel id
     */
    private String channelId;

    /**
     * user id
     */
    private String userId;

    /**
     * WalletCreatePayInRequestBO
     * 
     * @param requestBO
     * @return
     */
    public static ValidatorBO valueOf(WalletCreatePayInRequestBO requestBO, String userId) {
        if (Objects.isNull(requestBO)) {
            return null;
        }
        return ValidatorBO.builder().channelId(requestBO.getChannelId()).userId(userId).build();
    }
}
