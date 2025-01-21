package com.siupay.openapi.v1.service.impl;

import com.google.common.collect.Lists;
import com.siupay.common.api.dto.response.GenericResponse;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.common.api.utils.UserContextUtils;
import com.siupay.openapi.constant.DynamicConstants;
import com.siupay.openapi.v1.bo.request.RiskCardRequest;
import com.siupay.openapi.v1.constants.Constants;
import com.siupay.openapi.v1.constants.DictCode;
import com.siupay.openapi.v1.constants.ItemCode;
import com.siupay.openapi.v1.enums.RiskLocalBizTypeEnum;
import com.siupay.openapi.v1.service.CommonService;
import com.siupay.openapi.v1.service.RiskService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Slf4j
@Service
public class RiskServiceImpl implements RiskService {

    @Autowired
    private DynamicConstants dynamicConstants;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CommonService commonService;


    private static int RISK_SESSION_TIMEOUT = 1800; // second

    @Override
    public String getRiskSessionInfo(String userId, RiskLocalBizTypeEnum bizType) {
        String value = UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue()
                .set(getRiskKey(userId, bizType), value, RISK_SESSION_TIMEOUT, TimeUnit.SECONDS);
        return value;
    }


    @Override
    public String consumeSessionId(String userId, String sessionId, RiskLocalBizTypeEnum bizType) {
        Boolean consumeResult = Boolean.FALSE;
        String idFromRedis = null;
        try {
            idFromRedis = (String) redisTemplate.opsForValue().get(getRiskKey(userId, bizType));
            if (!StringUtils.isBlank(sessionId) && sessionId.equals(packFullSessionId(idFromRedis))) {
                consumeResult = redisTemplate.delete(getRiskKey(userId, bizType));
                log.info("删除sessionid:{}", sessionId);
            }
            log.info("请求的sessionId:{},用于比较的sessionId:{},userId{},bizType:{}", sessionId, packFullSessionId(idFromRedis),
                    userId, bizType.getCode());
        } catch (Exception e) {
            log.error(String.format("redis调用异常,请求参数userId:[%s],sessionId:[%s],bizType:[%s]", userId, sessionId,
                    bizType.getCode()), e);
        }
        Boolean rejectIfNotMatch =
                Boolean.valueOf(commonService.getValueByCodeAndKey(DictCode.RISK_INFO, ItemCode.SESSION_REJECT));
        if (rejectIfNotMatch && !consumeResult) {
            log.error(String.format("消费sessionId被拦截,请求参数userId:[%s],sessionId[%s],bizType:[%s]", userId, sessionId,
                    bizType));
            throw new PaymentException(ErrorCode.RISK_CONNECTION_FAILED);
        }
        return idFromRedis;    }

    private String getSessionIdIfExists(String userId, String sessionId, RiskLocalBizTypeEnum bizType) {
        String idFromRedis = null;
        try {
            idFromRedis = (String) redisTemplate.opsForValue().get(getRiskKey(userId, bizType));
            log.info("请求的sessionId:{},用于比较的sessionId:{},userId{},bizType:{}", sessionId, packFullSessionId(idFromRedis),
                    userId, bizType.getCode());
        } catch (Exception e) {
            log.error(String.format("redis调用异常,请求参数userId:[%s],sessionId:[%s],bizType:[%s]", userId, sessionId,
                    bizType.getCode()), e);
        }
        return idFromRedis;
    }

    private String getRiskKey(String userId, RiskLocalBizTypeEnum bizType) {
        StringBuilder key = new StringBuilder();
        key.append(dynamicConstants.getApplication())
                .append(":")
                .append(Constants.RISK_SESSION_ID)
                .append(":")
                .append(userId)
                .append(":")
                .append(bizType.getCode());
        return key.toString();
    }

    private String packFullSessionId(String sessionId) {
        if (StringUtils.isBlank(sessionId)) {
            return null;
        }
        Map<String, String> enabledKeyValueMapByCodes = commonService.getEnabledKeyValueMapByCodes(DictCode.RISK_INFO,
                Lists.newArrayList(ItemCode.MERCHANT_ID));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(enabledKeyValueMapByCodes.get(ItemCode.MERCHANT_ID)).append(sessionId);
        return stringBuilder.toString();
    }

    @Override
    public GenericResponse<Boolean> payinRiskDetect(RiskCardRequest riskCardRequest){

        kycCheck();
        amountCheck(riskCardRequest);
        return GenericResponse.success(Boolean.TRUE);
    }

    /**
     * PA-2568，风控前校验kyc
     */
    private void kycCheck() {
        String userId = UserContextUtils.getUserId();
        if (Objects.isNull(userId)) {
            throw new PaymentException(ErrorCode.USER_INFO_INVALID, "User kyc not exist");
        }
    }


    private void amountCheck(RiskCardRequest riskCardRequest){
        String tradeType = StringUtils.equalsIgnoreCase(riskCardRequest.getBizType(),RiskLocalBizTypeEnum.PRECHARGE.getCode())?RiskLocalBizTypeEnum.PRECHARGE.getCode() : "BUY";
    }
}
