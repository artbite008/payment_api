package com.siupay.openapi.v1.controller;//package com.siupay.openapi.v1.controller;
//
//import com.google.common.collect.Lists;
//import com.siupay.common.api.exception.ErrorCode;
//import com.siupay.common.api.exception.PaymentException;
//import com.siupay.common.api.utils.UserContextUtils;
//import com.siupay.common.lib.utils.BeanUtils;
//import com.siupay.openapi.bo.OrderHistoryRequestBo;
//import com.siupay.openapi.bo.OrderHistoryResponseBo;
//import com.siupay.openapi.controller.vo.request.OrderHistoryRequest;
//import com.siupay.openapi.controller.vo.response.OrderHistoryResponse;
//import com.siupay.openapi.util.IntegerConvertString;
//import com.siupay.openapi.v1.bo.response.FiatTradeHistoryResponse;
//import com.siupay.openapi.v1.constants.Constants;
//import com.siupay.openapi.v1.manager.OrderBaseManager;
//import com.siupay.starter.chaincontext.ChainRequestContext;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
///**
// * @author Uther.chen
// * @date 2022年04月08日
// */
//@Slf4j
//@RestController
//@Api("充值订单历史Api")
//public class OrderHistoryController {
//
//    @Autowired
//    private OrderBaseManager orderBaseManager;
//
//    /**
//     * 充值分页查询用户充值订单列表
//     *
//     * @param request
//     * @return
//     */
//    @ApiOperation("分页查询用户充值订单列表V1")
//    @GetMapping(value = {"/pmtapi/v1/payin_orders"}, params = {"orderType=RECHARGE"})
//    public GenericPageResult<OrderHistoryResponse> getOrderHistory(@Validated OrderHistoryRequest request) {
//        OrderHistoryRequestBo orderHistoryRequestBo = convertRequestToBo(request);
//
//        Pagination<OrderHistoryResponseBo> pagination = orderBaseManager.getOrderHistory(orderHistoryRequestBo);
//        List<OrderHistoryResponse> orderHistoryResponses = BeanUtils.copyListProperties(pagination.getItems(),
//                OrderHistoryResponse.class);
//
//        Pagination<OrderHistoryResponse> page = new Pagination(pagination.getCurrentPage(), pagination.getPageSize(),
//                pagination.getTotalNum(), orderHistoryResponses);
//        return GenericPageResult.success(page);
//    }
//
//    /**
//     * 分页查询用户快捷买币订单列表
//     *
//     * @param request
//     * @return
//     */
//    @ApiOperation("分页查询用户快捷买币订单列表V1")
//    @GetMapping(value = {"/pmtapi/v1/payin_orders"}, params = {"orderType=BUY"})
//    public GenericPageResult<FiatTradeHistoryResponse> getFastBuyOrderHistory(@Validated OrderHistoryRequest request) {
//
//        String orderId = request.getOrderId();
//        if (StringUtils.isEmpty(orderId)) {
//            // 未指定订单id，查询分页聚合payment-core和deposit
//            // 获取当前context，在两个异步任务中分别放入
//            ChainRequestContext currentContext = ChainRequestContext.getCurrentContext();
//
//            CompletableFuture<GenericPageResult<FiatTradeHistoryResponse>> futureOfPaymentCore =
//                    CompletableFuture.supplyAsync(() -> {
//                        ChainRequestContext.setRequestContext(currentContext);
//                        return queryPaymentCoreFastBuyHistory(request);
//                    });
//
//            CompletableFuture<GenericPageResult<FiatTradeHistoryResponse>> futureOfDeposit =
//                    CompletableFuture.supplyAsync(() -> {
//                        ChainRequestContext.setRequestContext(currentContext);
//                        return queryDepositBuyHistory(request);
//                    });
//
//            // 聚合数据
//            try {
//                return mergeResults(futureOfPaymentCore, futureOfDeposit);
//            } catch (PaymentException e) {
//                return GenericPageResult.fail(e.getCode(), e.getMessage());
//            } catch (Exception e) {
//                return GenericPageResult.fail(ErrorCode.SERVER_ERROR.getCode(), "query results error");
//            }
//        } else {
//            // 查询单个详情
//            if (request.getOrderId().startsWith(Constants.PAYIN_ORDER_PIR)) {
//                return queryPaymentCoreFastBuyHistory(request);
//            } else {
//                return queryDepositBuyHistory(request);
//            }
//        }
//    }
//
//    /**
//     * 将deposit和payment core数据合并
//     *
//     * @param futureOfPaymentCore
//     * @param futureOfDeposit
//     * @return
//     * @throws InterruptedException
//     * @throws ExecutionException
//     */
//    private GenericPageResult<FiatTradeHistoryResponse> mergeResults(CompletableFuture<GenericPageResult<FiatTradeHistoryResponse>> futureOfPaymentCore,
//                                                                     CompletableFuture<GenericPageResult<FiatTradeHistoryResponse>> futureOfDeposit)
//            throws InterruptedException, ExecutionException {
//        GenericPageResult<FiatTradeHistoryResponse> pageResult1 = futureOfPaymentCore.get();
//        GenericPageResult<FiatTradeHistoryResponse> pageResult2 = futureOfDeposit.get();
//
//        List<FiatTradeHistoryResponse> allRecords = Lists.newArrayList();
//        CollectionUtils.addAll(allRecords, pageResult1.getItems());
//        CollectionUtils.addAll(allRecords, pageResult2.getItems());
//        allRecords.sort((r1, r2) -> {
//            return r2.getCreatedAt().compareTo(r1.getCreatedAt());
//        });
//        // 总数取两个的最大，而不是综合，否则总页数不对
//        return GenericPageResult.success(
//                Integer.max(pageResult1.getCurrentPage(), pageResult2.getCurrentPage()),
//                pageResult1.getPageSize(),
//                Long.max(pageResult1.getTotalNum(), pageResult2.getTotalNum()),
//                allRecords);
//    }
//
//    /**
//     * 查询deposit历史
//     *
//     * @param orderHistoryRequest
//     * @return
//     */
//    private GenericPageResult<FiatTradeHistoryResponse> queryDepositBuyHistory(OrderHistoryRequest orderHistoryRequest) {
//        Pagination<FiatTradeHistoryResponse> page = new Pagination<>();
//
//        return GenericPageResult.success(page);
//    }
//
//    /**
//     * 查询payment core快捷买币
//     *
//     * @param request
//     * @return
//     */
//    private GenericPageResult<FiatTradeHistoryResponse> queryPaymentCoreFastBuyHistory(@Validated OrderHistoryRequest request) {
//        OrderHistoryRequestBo orderHistoryRequestBo = convertRequestToBo(request);
//
//        Pagination<FiatTradeHistoryResponse> pagination =
//                orderBaseManager.getFiatTradeHistoryResponse(orderHistoryRequestBo);
//        List<FiatTradeHistoryResponse> orderHistoryResponses = BeanUtils.copyListProperties(pagination.getItems(),
//                FiatTradeHistoryResponse.class);
//
//        Pagination<FiatTradeHistoryResponse> page = new Pagination(pagination.getCurrentPage(),
//                pagination.getPageSize(), pagination.getTotalNum(), orderHistoryResponses);
//        return GenericPageResult.success(page);
//    }
//
//    /**
//     * 分页查询用户充值订单列表
//     *
//     * @param request
//     * @return
//     */
//    @ApiOperation("分页查询用户充值订单列表V2")
//    @GetMapping(value = "/pmtapi/v2/payin_orders")
//    public GenericPageResult<OrderHistoryResponse> getOrderHistoryV2(@Validated OrderHistoryRequest request) {
//        OrderHistoryRequestBo orderHistoryRequestBo = convertRequestToBo(request);
//
//        Pagination<OrderHistoryResponseBo> pagination = orderBaseManager.getOrderHistory(orderHistoryRequestBo);
//        List<OrderHistoryResponse> orderHistoryResponses = BeanUtils.copyListProperties(pagination.getItems(),
//                OrderHistoryResponse.class);
//
//        Pagination<OrderHistoryResponse> page = new Pagination(pagination.getCurrentPage(), pagination.getPageSize(),
//                pagination.getTotalNum(), orderHistoryResponses);
//        return GenericPageResult.success(page);
//    }
//
//    @ApiOperation("单条明细查询")
//    @GetMapping(value = "/pmtapi/v1/payin_order/{orderId}")
//    public GenericResult<OrderHistoryResponse> getOrderById(@PathVariable String orderId) {
//        if (!StringUtils.isEmpty(orderId)) {
//            OrderHistoryResponseBo responseBo = orderBaseManager.getOrderById(orderId);
//            OrderHistoryResponse orderHistoryResponse = BeanUtils.copyProperties(responseBo,
//                    OrderHistoryResponse.class);
//            return GenericResult.success(orderHistoryResponse);
//        } else {
//            return GenericResult.fail(DepositErrorCode.VALIDATE_PARAMS_IS_NULL.getCode(),
//                    DepositErrorCode.VALIDATE_PARAMS_IS_NULL.getDescription());
//        }
//    }
//
//
//    private OrderHistoryRequestBo convertRequestToBo(@Validated OrderHistoryRequest request) {
//        OrderHistoryRequestBo orderHistoryRequestBo = BeanUtils.copyProperties(request, OrderHistoryRequestBo.class);
//        orderHistoryRequestBo.setUserId(UserContextUtils.getUserId());
//        orderHistoryRequestBo.setPageNum(request.getPage());
//        orderHistoryRequestBo.setPageSize(request.getPageSize());
//        orderHistoryRequestBo.setFiat(request.getFiat());
//        orderHistoryRequestBo.setCryptoCurrency(request.getCryptoCurrency());
//        orderHistoryRequestBo.setPaymentMethod(IntegerConvertString.bizSourceConvert(request.getBizSource()));
//        orderHistoryRequestBo.setOrderStatus(IntegerConvertString.payinStatusConvert(request.getOrderStatus()));
//        return orderHistoryRequestBo;
//    }
//
//}
