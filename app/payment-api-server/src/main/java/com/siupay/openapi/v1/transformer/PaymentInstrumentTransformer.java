package com.siupay.openapi.v1.transformer;

import com.siupay.common.lib.enums.ChannelEnum;
import com.siupay.instrument.dto.PaymentInstrumentBO;
import com.siupay.instrument.dto.address.Address;
import com.siupay.instrument.dto.billing.Billing;
import com.siupay.instrument.dto.card.Card;
import com.siupay.instrument.enums.BindStatus;
import com.siupay.openapi.v1.bo.response.ChannelCardInfoResponse;

public class PaymentInstrumentTransformer {
    public static ChannelCardInfoResponse toChannelCardInfoResponse(PaymentInstrumentBO bo) {
        ChannelCardInfoResponse channelCardInfoResponse = new ChannelCardInfoResponse();
        Card card = bo.getCard();
        if (card != null && card.getBilling() != null) {
            Billing billing = card.getBilling();
            if (billing.getAddress() != null) {
                Address address = billing.getAddress();
                channelCardInfoResponse.setAddress1(address.getAddress1());
                channelCardInfoResponse.setAddress2(address.getAddress2());
                channelCardInfoResponse.setAddress3(address.getAddress3());
                channelCardInfoResponse.setPostalCode(address.getPostalCode());
                channelCardInfoResponse.setState(address.getState());
                channelCardInfoResponse.setCountry(address.getCountry());
                channelCardInfoResponse.setCity(address.getCity());
            }
        }
        channelCardInfoResponse.setId(bo.getInstrumentId());
        if (card != null) {
            channelCardInfoResponse.setFirstName(card.getFirstName());
            channelCardInfoResponse.setLastName(card.getLastName());
            channelCardInfoResponse.setPrefix6(card.getBin());
            channelCardInfoResponse.setPostfix4(card.getLast4());
            channelCardInfoResponse.setScheme(card.getScheme());
            channelCardInfoResponse.setDesensitizedCard(card.getCardMaskNumber());
            channelCardInfoResponse.setIssuingBank(card.getIssuerBank());
            channelCardInfoResponse.setCardType(card.getCardType().name());
        }
        channelCardInfoResponse.setIsBinding(bo.getBindStatus() == BindStatus.BINDED);

        channelCardInfoResponse.setChannel(bo.getBindChannelId());
        channelCardInfoResponse.setChannelCardId(null);
        channelCardInfoResponse.setChannel(ChannelEnum.CHECKOUT_PCI.name());
        channelCardInfoResponse.setChannelType(bo.getPaymentMethod().name());
        channelCardInfoResponse.setMetaData(null);
//        channelCardInfoResponse.setSchemeUrl();
        return channelCardInfoResponse;
    }
}
