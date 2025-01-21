/*
 * Copyright 2019 Mek Global Limited
 */

package com.siupay.openapi.v1.constants;

import java.math.BigDecimal;

/**
 * 静态常量
 */
public class Constants {


    public static final String PAYIN_ORDER_PIR = "pir_";

    public static final String USER_ID = "USER_ID";
    public static final String RISK_CARD_ID = "RISK_CARD_ID";

    private Constants() {
        throw new IllegalArgumentException("禁止实例化");
    }

    public static final Integer VALIDATE_SUCCESS = 0;

    /**
     * 充值信用卡补偿点
     */
    public static final String COMPENSATION_GROUP_RECHARGE_CREDITCARD = "rechargeByCreditCard";

    /**
     * 渠道调用状态描述
     */
    public static final String CHANNEL_EXCEPTION = "CHANNEL_EXCEPTION";

    /**
     * 服务名称
     */
    public static final String APP_NAME = "DEPOSIT";

    /**
     * 调用第三方状态描述
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 调用第三方状态描述
     */
    public static final String FAIL = "FAIL";

    /**
     * 账务标示
     */
    public static final String ACCOUNT = "ACCOUNT";

    /**
     * 账务转账提示
     */
    public static final String ACCOUNT_TRANSFER = "ACCOUNT_TRANSFER";

    /**
     * 账务转账查询提示
     */
    public static final String ACCOUNT_TRANSFER_QUERY = "TRANSFER_QUERY";

    /**
     * 分割符
     */
    public static final String SEPARATOR = "_";

    /**
     * 出金标示
     */
    public static final String WITHDRAW = "WITHDRAW";

    /**
     * 入金标示
     */
    public static final String RECHARGE = "RECHARGE";

    /**
     * 账户上分主账户标示
     */
    public static final String ACCOUNT_TYPE_MAIN = "MAIN";

    /**
     * 账户默认tag
     */
    public static final String ACCOUNT_TAG_DEFAULT = "DEFAULT";

    /**
     * 账户手续费tag
     */
    public static final String ACCOUNT_FEE_TAG = "FIAT_CURRENCY_IN";

    /**
     * 账户上分biz
     */
    public static final String ACCOUNT_BIZ_TYPE = "FIAT_CURRENCY_IN";

    /**
     * 信用卡上分biz
     */
    public static final String BANK_CARD_ACCOUNT_BIZ_TYPE = "BANK_CARD_DEAL";

    /**
     * 登账bizType
     */
    public static final String BIZ_TYPE_CURRENCY_TALLY = "CURRENCY_TALLY";

    /**
     * 登账bizType
     */
    public static final String BIZ_TYPE_FIAT_BALANCE_DEAL = "FIAT_BALANCE_DEAL";

    /**
     * 账户上分domain
     */
    public static final String ACCOUNT_DOMAIN_ID = "siupay";

    /**
     * 账户返回成功状态码
     */
    public static final String ACCOUNT_INVOKE_CODE = "200";

    /**
     * 取消订单分布式锁前缀
     */
    public static final String CANCEL_ORDER_PREFIX = "deposit_cancel_order:";

    /**
     * 确认订单分布式锁前缀
     */
    public static final String CAPTURE_ORDER_PREFIX = "deposit_capture_order:";

    /**
     * 分控type类型
     */
    public static final String RISK_BIZ_TYPE = "fiat_order";

    /**
     * 渠道回调
     */
    public static final String CHANNEL_CALL_BACK = "callBack";

    /**
     * 渠道回调
     */
    public static final String CALL_BACK_ENUM = "eventEnum";
    /**
     * 卡中心删除
     */
    public static final Integer CARD_IS_DELETED = 1;
    /**
     * 是否绑定卡
     */
    public static final Integer CARD_IS_ACTIVE = 0;

    public static final String CHANNEL_BIZ_ID_NULL = "渠道业务id为空";

    /**
     * 信用卡买币补偿组
     */
    public static final String COMPENSATION_GROUP_FIAT_TRADE = "fiatTradeByCreditCard";

    /**
     * 法币余额买币补偿组
     */
    public static final String COMPENSATION_GROUP_FIAT_BALANCE_TRADE = "fiatTradeByBalance";

    /**
     * 默认的金额取舍方式：向下取舍
     */
    public static final Integer DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_DOWN;

    /**
     * 法币交易-余额买币-补偿-渠道
     */
    public static final String FIAT_TRADE_BALANCE_COMPENSATION_CHANNEL = "platform";

    /**
     * 法币交易-余额买币-补偿-渠道类型
     */
    public static final String FIAT_TRADE_BALANCE_COMPENSATION_CHANNEL_TYPE = "fait_balance";

    /**
     * 确认订单分布式锁前缀
     */
    public static final String LOCK_CARD_BUY_CRYPTO = "CARD_BUY_CRYPTO:";

    /**
     * 数据库desc描述信息截断长度：1020
     */
    public static final Integer DESC_LENGTH_1020 = 1020;

    /**
     * 平台数字货币账户余额低于阀值告警title
     */
    public static final String PLATFORM_FAIT_BALANCE_LESS_THAN_LIMIT_TITLE = "平台数字货币账户余额低于阀值";

    /**
     * 平台数字货币账户余额低于阀值告警content
     */
    public static final String PLATFORM_FAIT_BALANCE_LESS_THAN_LIMIT_CONTENT = "币种[{0}],阀值[{1}],当前余额[{2}]";

    /**
     * 登账操作人姓名默认值
     */
    public static final String FUNDS_RECORD_OPERATOR_NAME_DEFAULT = "--";

    /**
     * 登账操作默认备注信息
     */
    public static final String FUNDS_RECORD_REMARK_DEFAULT = "[Operator:{0}]";

    /**
     * 告警key
     */
    public static final String ALERT_KEY_PREFIX = "STR:" + Constants.APP_NAME + ":" + "BizAlert" + ":";

    /**
     * 信用卡买币转账remark
     */
    public static final String BANK_CARD_TRASFER_REMARK = "BANK_CARD_BUY_CRYPTO";

    /**
     * admin用户id
     */
    public static final String ADMIN_OPERATOR_ID = "operator";

    public static final String RISK_SESSION_ID = "RISK_SESSION_ID";

    public static final Integer ADDRESS_MAXLENGTH = 60;

    /**
     * 默认币种
     */
    public static final String DEFAULT_CURRENCY = "USD";

    /**
     * 加密方式
     */
    public static final String RSA = "RSA";

    /**
     * RSA instance
     */
    public static final String RSA_INSTANCE = "RSA";

    /**
     * 风控结果
     */
    public static final String RISK_RESULT = "RISK_RESULT";

    /**
     * 发送checkout预授权请求
     */
    public static final String CHECKOUT_SEND_CAPTURE = "checkout_send_capture";

    /**
     * 发送checkout取消预授权
     */
    public static final String CHECKOUT_SEND_VOID = "checkout_send_void";

    /**
     * 中文语音
     */
    public static final String ZH_CN = "zh_CN";

    /**
     * 繁体
     */
    public static final String ZH_HK = "zh_HK";

    /**
     * 默认算费策略优先级
     */
    public static final Integer DEFAULT_FEE_STRATEGY_PRIORITY = 0;

    /**
     * date formatyyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd HH:mm:ss";

    /**
     * UTC-8
     */
    public static final String LOCAL_UTC = "(UTC+08:00)";

    /**
     * date format yyyy-MM
     */
    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

    /**
     * 错误点格式：记录错误信息发生在什么地方
     */
    public static final String DEFAULT_ERROR_POINT_FORMAT = "errorPoint_{0}";

    /**
     * 格式化时间
     */
    public static final String DEFAULT_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * CONTENT
     */
    public static final String DOWNLOAD_CONTENT_TYPE = "application/octet-stream;charset=UTF-8";

    /**
     * ENCODING
     */
    public static final String DOWNLOAD_ENCODING = "utf8";

    /**
     * format
     */
    public static final String FORMAT_ENCODING = "UTF-8";

    /**
     * DISPOSITION
     */
    public static final String DOWNLOAD_DISPOSITION = "Content-Disposition";

    /**
     * DOWNLOAD_ATTACHMENT
     */
    public static final String DOWNLOAD_ATTACHMENT = "attachment; filename=";

    /**
     * sheetName
     */
    public static final String DOWNLOAD_SHEETNAME = "ORDER_HISTORY";

    /**
     * 订单历史记录下载每页默认条数
     */
    public static final Integer DOWNLOAD_PAGE_SIZE = 100;

    /**
     * 神策出入金类型
     */
    public static final String SENSORS_DEPOSIT_WITHDRAW_TYPE_DEPOSIT = "deposit";

    /**
     * 神策出入金方式：信用卡充值
     */
    public static final String SENSORS_DEPOSIT_WITHDRAW_METHOD_DEPOSIT = "BANK_CARD_DEPOSIT";

    /**
     * 神策出入金方式：信用卡买币
     */
    public static final String SENSORS_DEPOSIT_WITHDRAW_METHOD_BUY_CRYPTO = "BANK_CARD_BUY_CRYPTO";

    /**
     * 风控session错误
     */
    public static final String RISK_SESSION_ID_ERROR = "SESSION_ERROR";

    /**
     * 通知中心跳转target
     */
    public static final String NOTICE_JUMP_TARGET = "target";

    /**
     * 卡验证
     */
    public static final String CARD_VERIFICATION = "Card Verification";

    /**
     * 脱敏卡号拼接符号
     */
    public static final String CARD_SPLIC_SYMBOL = "****";

    /**
     * risk 前缀
     */
    public static final String RISK_SEESION_PREFIX = "jtbnhm";

    /**
     * 银行转账订单分布式锁前缀
     */
    public static final String LOCK_BANK_TRANSFER_DEPOSIT_ORDER = "BANK_TRANSFER_DEPOSIT_ORDER:";

    /**
     * 通知订单详情跳转链接
     */
    public static final String NOTICE_ORDER_DETAILS_LINK = "/otc/fiat/recharge/orderDetail?orderId=%s";

    /**
     * 预授权告警key
     */
    public static final String PRE_RECHARGE_ALTER_KEY = "user:preRecharge";

    /**
     * side type类型BUY
     * */
    public static final String BUY = "BUY";
}

