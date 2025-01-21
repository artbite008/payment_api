package com.siupay.openapi.v1.service.impl;

import com.siupay.openapi.v1.service.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService  {

    @Override
    public Map<String, String> getEnabledKeyValueMapByCodes(String dictCode, List<String> itemCodeList) {
        if (StringUtils.isBlank(dictCode) || CollectionUtils.isEmpty(itemCodeList)) {
            log.info("查询自动dictCode或item请求值为空");
            return null;
        }
        return Collections.emptyMap();
    }

    @Override
    public String getValueByCodeAndKey(String dictCode, String itemCode) {
        return null;
    }
}
