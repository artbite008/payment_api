package com.siupay.openapi.bo;

import java.util.List;

import lombok.Data;

@Data
public class PlatformAccountSummaryInfoResultBo {

    /**
     * 账户基本信息
     */
    List<PlatformAccountBaseInfoResultBo> accountBaseInfo;

    /**
     *支持查询的币种
     */
    private List<String> supportCurrencies;

    /**
     *支持的业务类型
     */
    private List<String> supportBizTypes;

    /**
     * 账务类型
     */
    private List<String> types;

    /**
     * 账务tag
     */
    private List<String> tags;
}
