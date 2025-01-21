package com.siupay.openapi.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author minn
 * @description
 * @date 2021/12/28
 */
@Data
@Component
public class DynamicConstants {

    /**
     * 银行转账 发起提现接口开关
     */
    @Value("${bank.transfer.payout.create.enable:true}")
    public Boolean bankTransferPayoutCreateEnable;

    /**
     * 用户接口全局限流，秒
     */
    @Value("${access.limit.global.user.seconds:1}")
    private int accessLimitGlobalUserSeconds;

    /**
     * 用户接口全局限流，最大访问数
     */
    @Value("${access.limit.global.user.max-count:100}")
    private int accessLimitGlobalUserMaxCount;

    /**
     * 用户接口，单个用户限流，秒
     */
    @Value("${access.limit.user.seconds:1}")
    private int accessLimitUserSeconds;

    /**
     * 用户接口，单个用户限流，最大访问数
     */
    @Value("${access.limit.user.max-count:1}")
    private int accessLimitUserMaxCount;

    /**
     * Admin接口限流，秒
     */
    @Value("${access.limit.admin.seconds:5}")
    private int accessLimitAdminSeconds;

    /**
     * Admin接口限流，最大访问数
     */
    @Value("${access.limit.admin.max-count:1}")
    private int accessLimitAdminMaxCount;
    /**
     * 卡绑定/解绑 risk event_code
     */
    @Value("${risk.card.binding.topic:PAYMENT_BIND_EVENT}")
    private String riskBindEventTopic;
    /**
     * 卡绑定/解绑 risk event_code
     */
    @Value("${risk.card.binding.code:50014811}")
    private Long cardCenterBindingRiskCode;

    /**
     * 风控消息version
     */
    @Value("${risk.event.version:1.0}")
    private String riskEventVersion;

    @Value("${open.white.list:true}")
    public Boolean whitelistSwitchOn;

    /**
     * 绑卡时区计算
     */
    @Value("${bind.card.timezone:0}")
    private Integer bindCardTimeZone;
    /**
     * 开关
     */
    @Value("#{${payment.api.channel.switch:{'WALLET_PAY_IN_SWITCH' : true}}}")
    private Map<String, Boolean> channelSwitchMap;

    /**
     * 服务名称
     */
    @Value("${spring.application.name}")
    private String application;


    /**
     * deposite 系统中fiat默认展示法币数据精度
     */
    @Value("${legacy.deposit.fiatPrecision:2}")
    private Integer fiatPrecision;

    /**
     *  reference缓存超时时间
     */
    @Value("${redis.timeout.reference:1800}")
    private Integer referenceTimeout;

    /**
     * channel-info缓存超时时间
     */
    @Value("${redis.timeout.channel-info:300}")
    private Integer channelInfoTimeout;

    /**
     * bankInfo缓存超时时间
     */
    @Value("${redis.timeout.bank_info:300}")
    private Integer bankInfoTimeout;

    @Value("#{${payment.api.out.limit:{'WEB' : 1,'ANDROID':1,'IOS':12}}}")
    private Map<String,Integer> outLimitMap;

    @Value("#{${payment.api.out.url.limit:{\"quotes\":{'WEB' : 1,'ANDROID':1,'IOS':12,'GLOBAL':10}}}}")
    private Map<String,Map<String,Integer>> urlLimitMap;

    /**
     * api 订单类限流规则
     */
    @Value("${order.rate.limiter.strategy:1/5 5 10 15}")
    private String orderRateLimiterStrategy;
}
