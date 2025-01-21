package com.siupay.openapi.v1.bo.request;

import com.siupay.instrument.dto.card.Card;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class BindCardInfoRequest {

//    @ApiModelProperty("渠道名称")
//    @NotBlank(message = "channel can't be null")
    private String channel;

    @ApiModelProperty("token")
    @NotBlank(message = " cardToken can't be null")
    private String cardToken;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("持卡人名")
    @NotBlank(message = "firstName can't be null")
    private String firstName;

    @ApiModelProperty("持卡人姓")
    @NotBlank(message = "lastName can't be null")
    private String lastName;

    @ApiModelProperty("有效期月，MM")
    @NotBlank(message = "expireMonth can't be null")
    private String expireMonth;

    @ApiModelProperty("有效期年，YYYY")
    @NotBlank(message = "expireYear can't be null")
    private String expireYear;

    @ApiModelProperty("仅用提现业务时，可空")
    @NotBlank(message = "address1 can't be null")
    // @Length(max = 120,message = "address length must less than 120")
    private String address1;

    @ApiModelProperty("可空")
    private String address2;

    @ApiModelProperty("可空")
    private String address3;

    @ApiModelProperty("邮编")
    @NotBlank(message = "postalCode can't be null")
    private String postalCode;

    @ApiModelProperty("城市")
    @NotBlank(message = "city can't be null")
    private String city;

    @ApiModelProperty("州")
    private String state;

    @ApiModelProperty("国家")
    @NotBlank(message = "country can't be null")
    private String country;

    @ApiModelProperty("卡组织，非空，如Visa, Master, …, Unknow")
    private String scheme;

    @ApiModelProperty("卡类型，非空，Credit, Debit, Prepaid, Commercial, …, Unkown")
    private String cardType;

    @ApiModelProperty("发卡行名称")
    private String issuingBank;

    @ApiModelProperty("发卡国家或地区")
    private String issuingBankCountry;

    @ApiModelProperty("后续可能使用")
    private Map<String, String> metaData;

    @ApiModelProperty("卡渠道类型:BANK_CARD")
    @NotBlank(message = "channelType can't be null")
    private String channelType;

    @ApiModelProperty("sessionId")
    private String riskSessionId;

    @ApiModelProperty("credential")
    private String credential;

    /**
     * 来源设备
     */
    @ApiModelProperty(value = "来源设备", example = "ANDROID,IOS,WEB,M_SITE")
//    @In(value = {"ANDROID", "IOS", "WEB", "M_SITE"})
    private String clientFrom = "WEB";

    @ApiModelProperty("新的设备指纹ID")
    private String fingerId;

    @ApiModelProperty("新的设备指纹主键")
    private String fingerPid;

    @ApiModelProperty("老的设备指纹ID")
    private String deviceId;

    private Card card;

    @ApiModelProperty("扩展参数")
    private Map<String, Object> ext;
}
