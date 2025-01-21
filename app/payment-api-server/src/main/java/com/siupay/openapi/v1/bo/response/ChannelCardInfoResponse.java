package com.siupay.openapi.v1.bo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

@Data
@ApiModel("用户展示支付卡")
public class ChannelCardInfoResponse {
    @ApiModelProperty("卡id")
    private String id;
    @ApiModelProperty("渠道名称")
    private String channel;
    @ApiModelProperty("渠道生成的卡ID，一张卡可存在多个ID，可空")
    private String channelCardId;
    @ApiModelProperty("渠道类型")
    private String channelType;
    @ApiModelProperty("持卡人名")
    private String firstName;
    @ApiModelProperty("持卡人姓")
    private String lastName;
    // @ApiModelProperty("加密卡号，PCI合规存储要求，未合规时为空")
    // private String encryptedCardNumber;
    @ApiModelProperty("脱敏卡号，仅记录卡号前六后四，如444444****8888")
    private String desensitizedCard;
    @ApiModelProperty("仅用提现业务时，可空")
    private String address1;
    @ApiModelProperty("可空")
    private String address2;
    @ApiModelProperty("可空")
    private String address3;
    @ApiModelProperty("邮编")
    private String postalCode;
    @ApiModelProperty("可空")
    private String city;
    @ApiModelProperty("州")
    private String state;
    @ApiModelProperty("村镇")
    private String country;
    @ApiModelProperty("卡组织，非空，如Visa, Master, …, Unknow")
    private String scheme;
    @ApiModelProperty("卡类型，非空，Credit, Debit, Prepaid, Commercial, …, Unkown")
    private String cardType;
    @ApiModelProperty("发卡行名称")
    private String issuingBank;
    @ApiModelProperty("后续可能使用")
    private Map<String, String> metaData;
    @ApiModelProperty("绑定中")
    private Boolean isBinding;
    @ApiModelProperty("图片")
    private String schemeUrl;
    @ApiModelProperty("前6")
    private String prefix6;
    @ApiModelProperty("后4")
    private String postfix4;
}

