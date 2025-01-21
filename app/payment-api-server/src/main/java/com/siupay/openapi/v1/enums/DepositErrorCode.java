package com.siupay.openapi.v1.enums;//package com.siupay.openapi.v1.enums;
//
//
//import com.siupay.common.api.exception.ErrorCode;
//import org.apache.commons.lang3.StringUtils;
//
//public enum DepositErrorCode implements ErrorCode {
//
//    /**
//     * 找不到渠道信息，请稍后重试
//     */
//    ROUTERS_EMPTY("800001", ErrorLevel.ERROR, "deposit.router.empty", Boolean.FALSE),
//    /**
//     * 找不到渠道信息，请稍后重试
//     */
//    SELECTOR_EMPTY("800002", ErrorLevel.ERROR, "deposit.selector.empty", Boolean.FALSE),
//    /**
//     * 找不到渠道信息，请稍后重试
//     */
//    CHANNEL_EMPTY("800003", ErrorLevel.ERROR, "deposit.channel.empty", Boolean.FALSE),
//    /**
//     * 创建订单失败，请稍后重试
//     */
//    CREATE_ORDER_ERROR("800004", ErrorLevel.ERROR, "deposit.create.order.error", Boolean.FALSE),
//    /**
//     * 创建订单失败，请稍后重试
//     */
//    CREATE_PRERECHARGE_ERROR("800005", ErrorLevel.ERROR, "deposit.create.prerecharge.error", Boolean.FALSE),
//    /**
//     * 预授权前置校验失败，请稍后重试
//     */
//    CHANNEL_PRERECHARGE_ERROR("800006", ErrorLevel.ERROR, "deposit.channel.prerecharge.error", Boolean.FALSE),
//    /**
//     * 确认订单失败，请稍后重新下单
//     */
//    CHANNEL_RECHARGE_CONFIRM_ERROR("800007", ErrorLevel.ERROR, "deposit.channel.recharge.confirm.error", Boolean.FALSE),
//    /**
//     * 账户上分失败，请稍后重新下单
//     */
//    ACCOUNT_RECHARGE_ERROR("800008", ErrorLevel.ERROR, "deposit.account.recharge.error", Boolean.FALSE),
//    /**
//     * 绑定银行卡id为空，请绑卡后重试
//     */
//    CHECKOUT_CARD_EMPTY("800009", ErrorLevel.ERROR, "deposit.checkout.card.empty", Boolean.FALSE),
//    /**
//     * 没有获取到manager
//     */
//    RECHARGE_MANAGER_EMPTY("800010", ErrorLevel.ERROR, "deposit.recharge.manager.empty", Boolean.FALSE),
//    /**
//     * 校验器参数为空，请稍后重试
//     */
//    VALIDATE_PARAMS_IS_NULL("800011", ErrorLevel.ERROR, "validate.params.is.null", Boolean.FALSE),
//    /**
//     * 订单不存在，请联系客服
//     */
//    ORDER_NOT_EXIST("800012", ErrorLevel.ERROR, "deposit.order.is.not.exist", Boolean.FALSE),
//    /**
//     * 用户注册地为空，请完善
//     */
//    USER_REGISTRATION_PLACE("800013", ErrorLevel.ERROR, "deposit.user.registration.place.empty", Boolean.FALSE),
//    /**
//     * 不支持的法币币种，请切换币种后重试
//     */
//    FIAT_NOT_SUPPORT("800014", ErrorLevel.ERROR, "deposit.channel.fiat.not.support", Boolean.FALSE),
//    /**
//     * 费率计算器计算错误
//     */
//    FEE_CALCULATE_ERROR("800015", ErrorLevel.ERROR, "deposit.fee.calculate.error", Boolean.FALSE),
//    /**
//     * 用户kyc信息为空, 请完善
//     */
//    USER_KYC_NOT_EXIST("800016", ErrorLevel.ERROR, "deposit.user.kyc.not.exist", Boolean.FALSE),
//    /**
//     * 用户kyc等级不够，请完善
//     */
//    KYC_LEVEL_NOT_SUPPORT("800017", ErrorLevel.ERROR, "deposit.user.kyc.not.support", Boolean.FALSE),
//    /**
//     * 用户未绑定手机，请完善
//     */
//    USER_NOT_BIND_PHONE("800018", ErrorLevel.ERROR, "deposit.user.not.bind.phone", Boolean.FALSE),
//    /**
//     * 用户未设置交易密码，请完善
//     */
//    USER_NOT_BIND_TRADE_PASSWORD("800019", ErrorLevel.ERROR, "deposit.user.not.bind.trade.password", Boolean.FALSE),
//    /**
//     * 用户信息不存在，请完善
//     */
//    USER_INFO_NOT_EXIST("800020", ErrorLevel.ERROR, "deposit.user.info.not.exist", Boolean.FALSE),
//    /**
//     * HTTP方法不支持
//     */
//    HTTP_METHOD_NOT_SUPPORT("800021", ErrorLevel.ERROR, "deposit.http.method.error", Boolean.FALSE),
//    /**
//     * 风控校验失败
//     */
//    RISK_CALL_FAIL("800022", ErrorLevel.ERROR, "deposit.risk.call.fail", Boolean.FALSE),
//
//    /**
//     * 充值授权取消
//     */
//    RECHARGE_CANCEL_AUTHORIZATION("800023", ErrorLevel.ERROR, "deposit.recharge.cancel.authorization", Boolean.FALSE),
//    /**
//     * 验证失败
//     */
//    VALIDATOR_ERROR("800024", ErrorLevel.ERROR, "deposit.validator_error", Boolean.FALSE),
//    /**
//     * 订单void异常
//     */
//    CHANNEL_VOID_ERROR("800025", ErrorLevel.ERROR, "deposit.order.void.error", Boolean.FALSE),
//    /**
//     * 订单capture异常
//     */
//    CHANNEL_CAPTURE_ERROR("800026", ErrorLevel.ERROR, "deposit.order.capture.error", Boolean.FALSE),
//    /**
//     * 订单delete异常
//     */
//    CARD_DELETE_ERROR("800027", ErrorLevel.ERROR, "deposit.card.delete.error", Boolean.FALSE),
//    /**
//     * 更新绑卡信息异常
//     */
//    CARD_BINDING_UPDATE_ERROR("800028", ErrorLevel.ERROR, "deposit.card.binding.update.error", Boolean.FALSE),
//    /**
//     * 订单关闭异常
//     */
//    CHANNEL_ORDER_OPEN_VALIDATOR("800029", ErrorLevel.ERROR, "deposit.order.close.error", Boolean.FALSE),
//    /**
//     * 法币回调异常
//     */
//    CALLBACK_CALCULATE_ERROR("800030", ErrorLevel.ERROR, "deposit.callback.calculate.error", Boolean.FALSE),
//    /**
//     * 去到类型不支持
//     */
//    CHANNEL_TYPE_NOT_SUPPORT("800031", ErrorLevel.ERROR, "deposit.channel.type.not.support", Boolean.FALSE),
//    /**
//     * 卡验证失败
//     */
//    CARD_VERIFY_ERROR("800032", ErrorLevel.ERROR, "deposit.card.verify.fail", Boolean.FALSE),
//    /**
//     * 回调异常
//     */
//    CHANNEL_CALL_BACK_NULL("800033", ErrorLevel.ERROR, "deposit.order.callback.error", Boolean.FALSE),
//    /**
//     * 支付方式为空
//     */
//    PAYMENT_EMPTY("800034", ErrorLevel.ERROR, "deposit.payment.empty", Boolean.FALSE),
//    /**
//     * 风控session异常
//     */
//    RISK_SESSION_ERROR("800101", ErrorLevel.ERROR, "deposit.risk.session.error", Boolean.FALSE),
//    /**
//     * 风控拒绝
//     */
//    RISK_RESULT_REJECT("800102", ErrorLevel.ERROR, "deposit.risk.result.reject", Boolean.FALSE),
//    /**
//     * 查询余额失败
//     */
//    QUERY_BALANCE_FAIL("800035", ErrorLevel.ERROR, "deposit.query.balance.fail", Boolean.FALSE),
//    /**
//     * 客户余额不足
//     */
//    CUSTOMER_NO_BALANCE("800036", ErrorLevel.ERROR, "deposit.customer.no.balance", Boolean.FALSE),
//    /**
//     * 平台余额不足
//     */
//    PLATFORM_NO_BALANCE("800037", ErrorLevel.ERROR, "deposit.platform.no.balance", Boolean.FALSE),
//    /**
//     * 获取报价失败
//     */
//    OBTAIN_QUOTE_FAIL("800038", ErrorLevel.ERROR, "deposit.obtain.quote.fail", Boolean.FALSE),
//    /**
//     * 获取账务结果失败
//     */
//    OBTAIN_ACCOUNT_RESULT_FAIL("800039", ErrorLevel.ERROR, "deposit.account.result.unknown", Boolean.FALSE),
//    /**
//     * 账务操作不能为空
//     */
//    ACCOUNT_OPERATION_EMPTY("800041", ErrorLevel.ERROR, "deposit.account.operation.empty", Boolean.FALSE),
//    /**
//     * 法币交易失败
//     */
//    FIAT_TRADE_ERROR("800042", ErrorLevel.ERROR, "deposit.fiat.trade.error", Boolean.FALSE),
//    /**
//     * 交易订单为空
//     */
//    TRADE_ORDER_NULL("800043", ErrorLevel.ERROR, "deposit.trade.order.empty", Boolean.FALSE),
//    /**
//     * 接口关闭
//     */
//    API_SWITCH_NOT_PEN("800044", ErrorLevel.ERROR, "deposit.api.switch.not.open", Boolean.FALSE),
//    /**
//     * 购买方式不正确
//     */
//    FAIT_BALANCE_NO_AMOUNT("800045", ErrorLevel.ERROR, "deposit.fait.balance.no.amount", Boolean.FALSE),
//    /**
//     * 余额买币不支持该交易对
//     */
//    FAIT_BALANCE_NO_SUPPORT("800046", ErrorLevel.ERROR, "deposit.fait.balance.not.support", Boolean.FALSE),
//    /**
//     * 金额超过最大限制
//     */
//    FAIT_AMOUNT_OVER_LIMIT("800047", ErrorLevel.ERROR, "deposit.amount.over.limit", Boolean.FALSE),
//
//    /**
//     * 金额低于最低限制
//     */
//    FAIT_AMOUNT_BELOW_LIMIT("800075", ErrorLevel.ERROR, "deposit.amount.below.limit", Boolean.FALSE),
//    /**
//     * 金额超过日限额
//     */
//    FAIT_AMOUNT_OVER_DAY_LIMIT("800048", ErrorLevel.ERROR, "deposit.amount.over.day.limit", Boolean.FALSE),
//    /**
//     * 查询账户余额信息失败
//     */
//    FAIT_AMOUNT_BALANCE_QUERY_FAIL("800049", ErrorLevel.ERROR, "deposit.query.account.balance.fail", Boolean.FALSE),
//    /**
//     * 请求参数不正确
//     */
//    INPUT_PARAMETER_ERROR("800050", ErrorLevel.ERROR, "deposit.input.parameter.error", Boolean.FALSE),
//    /**
//     * 没有操作权限
//     */
//    CURRENT_USER_NO_RIGHT("800051", ErrorLevel.ERROR, "deposit.current.user.no.right", Boolean.FALSE),
//    /**
//     * 平台账户不允许该币种进行交易
//     */
//    PLATFORM_ACCOUNT_CURRENCY_CANNOT_TRADE("800052", ErrorLevel.ERROR, "deposit.platform.account.currency.cannot.trade",
//            Boolean.FALSE),
//    /**
//     * 该账户不是平台账户
//     */
//    PLATFORM_ACCOUNT_NOT_CONTAIN("800053", ErrorLevel.ERROR, "deposit.platform.account.not.contain", Boolean.FALSE),
//    /**
//     * 用户信息查询失败
//     */
//    QUERY_USER_INFO_FAIL("800054", ErrorLevel.ERROR, "deposit.query.user.info.fail", Boolean.FALSE),
//    /**
//     * 重复交易
//     */
//    REPEAT_TRADE("800055", ErrorLevel.ERROR, "deposit.repeat.trade", Boolean.FALSE),
//    /**
//     * 交易失败，请稍后查询
//     */
//    TRADE_FAIL("800056", ErrorLevel.ERROR, "deposit.trade.fail", Boolean.FALSE),
//    /**
//     * 交易黑名单限制
//     */
//    BLACK_LIST_LIMIT("800057", ErrorLevel.ERROR, "deposit.black.list.limit", Boolean.FALSE),
//    /**
//     * 银行卡不支持
//     */
//    BANK_CARD_CURRENCY_NO_SUPPORT("800058", ErrorLevel.ERROR, "deposit.bank.card.not.support", Boolean.FALSE),
//    /**
//     * 查询失败，请稍后再试
//     */
//    QUERY_FAIL("800059", ErrorLevel.ERROR, "deposit.query.fail", Boolean.FALSE),
//    /**
//     * channelId为空
//     */
//    RECHARGE_CHANNEL_ID_EMPTY("800060", ErrorLevel.ERROR, "deposit.recharge.channelId.empty", Boolean.FALSE),
//    /**
//     * 白名单验证没有通过
//     */
//    WHITELIST_VERIFY_NO_PASS("800061", ErrorLevel.ERROR, "deposit.whitelist.verify.no.pass", Boolean.FALSE),
//    /**
//     * 黑名单验证没有通过
//     */
//    BLACKLIST_VERIFY_NO_PASS("800062", ErrorLevel.ERROR, "deposit.blacklist.verify.no.pass", Boolean.FALSE),
//    /**
//     * 账务转账失败
//     */
//    ACCOUNT_TRANSFER_FAIL("800063", ErrorLevel.ERROR, "deposit.account.transfer.fail", Boolean.FALSE),
//    /**
//     * 报价信息错误
//     */
//    QUOTE_INFO_WRONG("800064", ErrorLevel.ERROR, "deposit.quote.info.wrong", Boolean.FALSE),
//    /**
//     * 更新订单失败
//     */
//    UPDATE_ORDER_ERROR("800065", ErrorLevel.ERROR, "deposit.update.order.error", Boolean.FALSE),
//    /**
//     * 订单状态异常
//     */
//    ORDER_STATUS_ERROR("800066", ErrorLevel.ERROR, "deposit.order.status.error", Boolean.FALSE),
//    /**
//     * 客户转账失败
//     */
//    CUSTOMER_TRANSFER_FAIL("800067", ErrorLevel.ERROR, "deposit.customer.transfer.fail", Boolean.FALSE),
//    /**
//     * 订单异常
//     */
//    ORDER_EXCEPTION("800068", ErrorLevel.ERROR, "deposit.order.exception", Boolean.FALSE),
//    /**
//     * 补偿信息异常
//     */
//    COMPENSATION_RECORD_EXCEPTION("800069", ErrorLevel.ERROR, "deposit.compensation.record.exception", Boolean.FALSE),
//    /**
//     * 支付方式不支持
//     */
//    PAYMENT_METHOD_NOT_OPEN("800070", ErrorLevel.ERROR, "deposit.payment.method.not.open", Boolean.FALSE),
//    /**
//     * 风控结果不支持
//     */
//    ENUM_NOT_MAP("800071", ErrorLevel.ERROR, "deposit.enum.not.map", Boolean.FALSE),
//    /**
//     * 风控校验异常
//     */
//    RISK_CHECK_ERROR("800072", ErrorLevel.ERROR, "deposit.risk.check.error", Boolean.FALSE),
//    /**
//     * 风控消息解密异常
//     */
//    RSA_ERROR("800073", ErrorLevel.ERROR, "deposit.message.rsa.error", Boolean.FALSE),
//    /**
//     * 有变异常
//     */
//    POSTCODE_NOT_VALID("800074", ErrorLevel.ERROR, "deposit.postcode.not.valid", Boolean.FALSE),
//    /**
//     * 交易类型不支持
//     */
//    TRADE_TYPE_NOT_SUPPORT("800076", ErrorLevel.ERROR, "deposit.tradeType.not.support", Boolean.FALSE),
//
//    /**
//     * 卡已经绑定
//     */
//    CARD_ALREADY_BINDING_ERROR("800077", ErrorLevel.ERROR, "deposit.card.already.binding.error", Boolean.FALSE),
//
//    CVV_CHECK_DECLINE("800078", ErrorLevel.ERROR, "deposit.cvv.check.decline", Boolean.FALSE),
//
//    /**
//     * admin后台导出
//     */
//    ADMIN_DOWNLOAD_ERROR("800079", ErrorLevel.ERROR, "订单列表导出失败！请重试！", Boolean.FALSE),
//
//    /**
//     * 信用卡买币后置总线处理失败
//     */
//    BANK_CARD_AFTER_EVENT_ERROR("800080", ErrorLevel.ERROR, "信用卡买币后置总线处理失败！", Boolean.FALSE),
//
//    /**
//     * 黑名单校验异常
//     */
//    CARD_BLACK_CHECK_ERROR("800081", ErrorLevel.WARN, "deposit.card.black.check.error", Boolean.FALSE),
//
//    /**
//     * 绑卡kyc验证不匹配
//     */
//    CARD_BIND_KYC_NAME_ERROR("800082",ErrorLevel.ERROR,"deposit.card.bind.kyc.name.error",Boolean.FALSE),
//    /**
//     *  法币转账失败
//     */
//    ADMIN_FIAT_TRANSFER_ERROR("800083",ErrorLevel.ERROR,"deposit.admin.fiat.transfer.error",Boolean.FALSE),
//
//    ADMIN_FIAT_TRANSFER_AMOUNT_ERROR("800084",ErrorLevel.ERROR,"deposit.admin.fiat.transfer.amount.error", Boolean.FALSE),
//
//    /**
//     * PaymentError 内部技术错误4XXX 通用映射错误
//     */
//    CHANNEL_ERROR("804000", ErrorLevel.ERROR, "deposit.payment.channle.error", Boolean.FALSE),
//    /**
//     * PaymentError 内部技术错误19XX 通用映射错误
//     */
//    SYSTEM_UNAVAILABLE("801900", ErrorLevel.ERROR, "deposit.payment.system.unavailable.error", Boolean.FALSE),
//    /**
//     * 通用错误信息，放在最后，其余的都往中间填充
//     */
//    COMMON_UNKNOWN_ERROR("888888", ErrorLevel.ERROR, "deposit.unknown.error", Boolean.FALSE);
//
//
//
//    DepositErrorCode(String code, ErrorLevel errorLevel, String desc, Boolean isRetry) {
//        this.code = code;
//        this.errorLevel = errorLevel;
//        this.desc = desc;
//        this.isRetry = isRetry;
//    }
//
//    private String code;
//
//    private String desc;
//
//    private ErrorLevel errorLevel;
//
//    private Boolean isRetry;
//
//
//    @Override
//    public ErrorType getErrorType() {
//        return ErrorType.BIZ;
//    }
//
//    @Override
//    public ErrorLevel getErrorLevel() {
//        return errorLevel;
//    }
//
//    @Override
//    public String getCode() {
//        return code;
//    }
//
//    @Override
//    public String getDescription() {
//        return desc;
//    }
//
//    @Override
//    public boolean isRetry() {
//        return isRetry;
//    }
//
//    /**
//     * 根据name获取ErrorCode
//     *
//     * @param name
//     * @return
//     */
//    public static ErrorCode getErrorCodeByName(String name) {
//        if (StringUtils.isBlank(name)) {
//            return null;
//        }
//        return DepositErrorCode.valueOf(name);
//    }
//}
