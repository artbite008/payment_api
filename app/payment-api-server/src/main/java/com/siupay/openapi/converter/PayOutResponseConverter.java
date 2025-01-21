package com.siupay.openapi.converter;

import com.siupay.common.api.dto.response.BasePaginationResponse;
import com.siupay.common.lib.utils.BeanUtils;
import com.siupay.openapi.bo.PayOutOrderResponseBO;
import com.siupay.openapi.bo.PayoutAccountResultBo;
import com.siupay.openapi.controller.vo.response.PayOutOrderResponse;
import com.siupay.openapi.controller.vo.response.PayoutAccountAdminResponse;
import lombok.experimental.UtilityClass;

/**
 * @program: payment-api
 * @description: 返回参数转换
 * @author: Sandy
 **/
@UtilityClass
public class PayOutResponseConverter {

    /**
     * 提现账户返回信息转换
     * 
     * @param resultBoPagination
     * @return
     */
    public static BasePaginationResponse<PayoutAccountAdminResponse> converAccounts(
            BasePaginationResponse<PayoutAccountResultBo> resultBoPagination) {
        return new BasePaginationResponse<>(resultBoPagination.getCurrentPage(), resultBoPagination.getPageSize(),
                resultBoPagination.getTotalNum(),
                BeanUtils.copyListProperties(resultBoPagination.getItems(), PayoutAccountAdminResponse.class));
    }

    /**
     * 提现订单列表查询转换
     *
     * @param resultBoPagination
     * @return
     */
    public static BasePaginationResponse<PayOutOrderResponse> converOrders(BasePaginationResponse<PayOutOrderResponseBO> resultBoPagination) {
        return new BasePaginationResponse<>(resultBoPagination.getCurrentPage(), resultBoPagination.getPageSize(),
                resultBoPagination.getTotalNum(),
                BeanUtils.copyListProperties(resultBoPagination.getItems(), PayOutOrderResponse.class));
    }
}
