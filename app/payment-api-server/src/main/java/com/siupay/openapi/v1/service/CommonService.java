package com.siupay.openapi.v1.service;

import java.util.List;
import java.util.Map;

public interface CommonService {

    /**
     * 批量获取值
     * 
     * @param dictCode
     * @param itemCodeList
     * @return
     */
    Map<String, String> getEnabledKeyValueMapByCodes(String dictCode, List<String> itemCodeList);

    /**
     * 直接获取值
     *
     * @param dictCode
     * @param itemCode
     * @return
     */
    String getValueByCodeAndKey(String dictCode, String itemCode);
}
