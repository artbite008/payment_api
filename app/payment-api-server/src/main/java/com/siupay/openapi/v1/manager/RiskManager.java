package com.siupay.openapi.v1.manager;

import com.google.common.collect.Lists;
import com.siupay.openapi.v1.constants.DictCode;
import com.siupay.openapi.v1.constants.ItemCode;
import com.siupay.openapi.v1.bo.RiskSessionResponseBO;
import com.siupay.openapi.v1.enums.RiskLocalBizTypeEnum;
import com.siupay.openapi.v1.service.CommonService;
import com.siupay.openapi.v1.service.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RiskManager {
    @Autowired
    private RiskService riskService;
    @Autowired
    private CommonService commonService;


    public RiskSessionResponseBO getRiskSessionInfo(String userId, String bizType) {
        Map<String, String> enabledKeyValueMapByCodes = commonService.getEnabledKeyValueMapByCodes(DictCode.RISK_INFO,
                Lists.newArrayList(ItemCode.RISK_CYBS_URL, ItemCode.MERCHANT_ID));
        String sessionId = riskService.getRiskSessionInfo(userId, RiskLocalBizTypeEnum.getEnumByCode(bizType));
        return new RiskSessionResponseBO(sessionId, enabledKeyValueMapByCodes.get(ItemCode.MERCHANT_ID),
                enabledKeyValueMapByCodes.get(ItemCode.RISK_CYBS_URL));
    }
}
