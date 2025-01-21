package com.siupay.openapi.v1.manager;

import com.google.common.collect.Maps;
import com.siupay.common.api.dto.response.BasePaginationResponse;
import com.siupay.common.api.enums.PaymentSystem;
import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentError;
import com.siupay.common.api.exception.PaymentException;
import com.siupay.common.lib.enums.ChannelEnum;
import com.siupay.common.lib.json.JsonUtils;
import com.siupay.core.PayinOrderQueryApi;
import com.siupay.core.common.dto.Paging;
import com.siupay.core.dto.PayinFilterDto;
import com.siupay.core.dto.PayinOrderDto;
import com.siupay.core.dto.PayinOrderQueryHistoryRequest;
import com.siupay.enums.PayinOrderStatusEnum;
import com.siupay.openapi.bo.OrderHistoryRequestBo;
import com.siupay.openapi.bo.OrderHistoryResponseBo;
import com.siupay.openapi.util.StringConvertInteger;
import com.siupay.openapi.v1.bo.response.FiatTradeHistoryResponse;
import com.siupay.openapi.v1.service.OrderService;
import com.siupay.openapi.v1.constants.Constants;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class OrderBaseManager {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PayinOrderQueryApi payinOrderQueryApi;


    /**
     * 查询历史
     *
     * @param orderHistoryBo
     * @return
     */
    public BasePaginationResponse<OrderHistoryResponseBo> getOrderHistory(OrderHistoryRequestBo orderHistoryBo) {
        PayinOrderQueryHistoryRequest request = buildHistoryPageRequest(orderHistoryBo);
        Either<PaymentError, Paging<PayinOrderDto>> either = payinOrderQueryApi.payinOrderQueryHistory(request);
        if (either.isLeft()) {
            throw new PaymentException(ErrorCode.getErrorCodeByCode(either.getLeft().getCode()),
                    either.getLeft().getMsg(), PaymentSystem.PAYMENT_API);
        }
        Paging<PayinOrderDto> paging = either.get();

        List<PayinOrderDto> payinRecords =
                Optional.ofNullable(paging).map(Paging::getRecords).orElse(Collections.emptyList());
        List<OrderHistoryResponseBo> items = Collections.emptyList();

        if (CollectionUtils.isNotEmpty(payinRecords)) {
            // 设置支付方式名称，cko只能用channelType查询，sepa只能用channelName查询，为了兼容，只有两个字段一起查
            items = payinRecords.stream().map(this::convert).collect(Collectors.toList());
            List<String> channelIds = items.stream()
                    .map(OrderHistoryResponseBo::getChannelType)
                    .distinct()
                    .collect(Collectors.toList());
            Map<String, String> paymentMethodConfigMap = getPaymentMethodConfigMap(channelIds);
            items.forEach(responseBo -> {
                String paymentName = paymentMethodConfigMap.get(responseBo.getChannelType());
                if (StringUtils.isBlank(paymentName)) {
                    return;
                }
                responseBo.setChannelType(paymentName);
            });
        }
        return new BasePaginationResponse(paging.getPageIndex().intValue(), paging.getPageSize().intValue(), paging.getTotalNum()
                , items);
    }

    /**
     * 查询快捷买币历史
     *
     * @param orderHistoryBo
     * @return
     */
    public BasePaginationResponse<FiatTradeHistoryResponse> getFiatTradeHistoryResponse(OrderHistoryRequestBo orderHistoryBo) {
        PayinOrderQueryHistoryRequest request = buildHistoryPageRequest(orderHistoryBo);
        Either<PaymentError, Paging<PayinOrderDto>> either = payinOrderQueryApi.payinOrderQueryHistory(request);
        if (either.isLeft()) {
            throw new PaymentException(ErrorCode.getErrorCodeByCode(either.getLeft().getCode()),
                    either.getLeft().getMsg(), PaymentSystem.PAYMENT_API);
        }
        Paging<PayinOrderDto> paging = either.get();

        List<PayinOrderDto> payinRecords =
                Optional.ofNullable(paging).map(Paging::getRecords).orElse(Collections.emptyList());
        List<FiatTradeHistoryResponse> items = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(payinRecords)) {
            payinRecords.forEach(item -> {
                try {
                    items.add(covert(item));
                } catch (Exception e) {
                    log.error("covert error {}", JsonUtils.toJson(item));
                }
            });
        }

        List<String> channelIds = items.stream()
                .map(FiatTradeHistoryResponse::getPaymentName)
                .distinct()
                .collect(Collectors.toList());
        Map<String, String> paymentMethodConfigMap = getPaymentMethodConfigMap(channelIds);
        items.forEach(responseBo -> {
            String paymentName = paymentMethodConfigMap.get(responseBo.getPaymentName());
            if (StringUtils.isBlank(paymentName)) {
                return;
            }
            responseBo.setPaymentName(paymentName);
        });

        return new BasePaginationResponse(paging.getPageIndex().intValue(), paging.getPageSize().intValue(), paging.getTotalNum()
                , items);
    }

    public OrderHistoryResponseBo getOrderById(String orderId) {
        Either<PaymentError, PayinOrderDto> either = payinOrderQueryApi.payinOrderQuery(orderId);
        if (either.isLeft()) {
            throw either.getLeft().toPaymentException();
        }
        PayinOrderDto payinOrderDto = either.get();
        OrderHistoryResponseBo convert = convert(payinOrderDto);
        ChannelEnum channelEnum = ChannelEnum.get(payinOrderDto.getChannelId());
        convert.setChannelName(channelEnum.name());
        convert.setChannelType(getPaymentMethodEnName(channelEnum));
        return convert;
    }

    /**
     * 转换为bo
     *
     * @param payinOrderDto
     * @return
     */
    private OrderHistoryResponseBo convert(PayinOrderDto payinOrderDto) {
        OrderHistoryResponseBo orderHistoryResponseBo = new OrderHistoryResponseBo();
        orderHistoryResponseBo.setChannelType(ChannelEnum.get(payinOrderDto.getChannelId()).name());

        orderHistoryResponseBo.setOrderId(payinOrderDto.getOrderId());
        orderHistoryResponseBo.setCoin(payinOrderDto.getPayinAmount().getCurrency());
        orderHistoryResponseBo.setAmount(payinOrderDto.getPayinAmount().getAmount());
        PayinOrderStatusEnum payinOrderStatusEnum = PayinOrderStatusEnum.valueOf(payinOrderDto.getStatus());
        orderHistoryResponseBo.setStatus(StringConvertInteger.payinStatusConvert(payinOrderStatusEnum));
        orderHistoryResponseBo.setAppealStatus(0);
        orderHistoryResponseBo.setTotalFee(payinOrderDto.getFeeAmount().getAmount());
        orderHistoryResponseBo.setCreateTime(payinOrderDto.getCreateTime());
        return orderHistoryResponseBo;
    }

    /**
     * 构建查询请求
     *
     * @param orderHistoryBo
     * @return
     */
    private PayinOrderQueryHistoryRequest buildHistoryPageRequest(OrderHistoryRequestBo orderHistoryBo) {
        PayinFilterDto filter = new PayinFilterDto();
        filter.setOrderType(orderHistoryBo.getOrderType());
        filter.setFiatCurrency(orderHistoryBo.getFiat());
        filter.setOrderId(orderHistoryBo.getOrderId());
        filter.setCryptoCurrency(orderHistoryBo.getCryptoCurrency());
        filter.setStatus(orderHistoryBo.getOrderStatus());
        filter.setPaymentMethod(orderHistoryBo.getPaymentMethod());
        PayinOrderQueryHistoryRequest request = new PayinOrderQueryHistoryRequest();
        request.setFilter(filter);
        request.setCurrentPage(orderHistoryBo.getPageNum());
        request.setPageSize(orderHistoryBo.getPageSize());
        return request;
    }

    /**
     * 查询paymentmethod的名称映射
     *
     * @param channelIds
     * @return
     */
    private Map<String, String> getPaymentMethodConfigMap(List<String> channelIds) {
        return Maps.newHashMap();

    }

    /**
     * 接口转换
     */
    private FiatTradeHistoryResponse covert(PayinOrderDto payinOrderDto) {
        FiatTradeHistoryResponse response = new FiatTradeHistoryResponse();

        BigDecimal depositFiatAmount = payinOrderDto.getDepositFiatAmount().getAmount();
        BigDecimal depositCryptoAmount = payinOrderDto.getDepositCryptoAmount().getAmount();
        BigDecimal price = depositFiatAmount.divide(depositCryptoAmount, 2, RoundingMode.DOWN);
        response.setPrice(price);
        response.setBizSource(StringConvertInteger.bizSourceConvert(payinOrderDto.getPaymentMethod()));
        response.setOrderStatus(StringConvertInteger.payinStatusConvert(PayinOrderStatusEnum.valueOf(payinOrderDto.getStatus())));
        //channel id 对前端来说是payment method name
        response.setPaymentName(ChannelEnum.get(payinOrderDto.getChannelId()).name());
        //todo 默认是buy类型，后续根据payin or payout修改
        response.setSide(Constants.BUY);
        response.setRefType(StringConvertInteger.refTypeConvert(Constants.BUY));
        response.setFee(payinOrderDto.getFeeAmount().getAmount());

        response.setCryptoCurrency(payinOrderDto.getDepositCryptoAmount().getCurrency());
        response.setCryptoQuantity(payinOrderDto.getDepositCryptoAmount().getAmount());
        response.setFiatAmount(payinOrderDto.getPayinAmount().getAmount());
        response.setFiatCurrency(payinOrderDto.getPayinAmount().getCurrency());
        response.setCreatedAt(payinOrderDto.getCreateTime());
        response.setOrderId(payinOrderDto.getOrderId());

        return response;
    }

    /**
     * 将channelId转为paymentmethod的名称映射
     *
     * @param channelEnum
     * @return
     */
    public String getPaymentMethodEnName(ChannelEnum channelEnum) {
        String channelName = channelEnum.name();

        return channelName;
    }
}
