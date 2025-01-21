package com.siupay.openapi.v1.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;

public class RechargeOrderDetailsDO {
    /**
     * 流水id
     */
    private String id;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 充值法币币种
     */
    private String fiatCurrency;

    /**
     * 用户充值金额
     */
    private BigDecimal rechargeAmount;

    /**
     * 渠道名称
     */
    private String channelName;

    /**
     * 渠道类型
     */
    private String channelType;

    /**
     * 渠道业务id
     */
    private String channelBizId;

    /**
     * 渠道充值状态(0成功1失败2.进行中)
     */
    private Integer channelRechargeStatus;

    /**
     * 渠道充值完成时间
     */
    private Date channelStatusTime;

    /**
     * 渠道状态描述
     */
    private String channelStatusDesc;

    /**
     * 渠道手续费类型
     */
    private String channelFeeTag;

    /**
     * 渠道手续费
     */
    private BigDecimal channelFeeAmount;

    /**
     * 渠道确认充值金额
     */
    private BigDecimal channelRechargebackAmount;

    /**
     * 平台手续费类型
     */
    private String platformFeeTag;

    /**
     * 平台手续费
     */
    private BigDecimal platformFeeAmount;

    /**
     * 账户系统业务id
     */
    private String accountBizId;

    /**
     * 充值后账户可用余额
     */
    private BigDecimal accountUserBalance;

    /**
     * 账户系统确认充值金额
     */
    private BigDecimal accountRechargebackAmount;

    /**
     * 账户系统充值状态(0成功1失败)
     */
    private Integer accountRechargeStatus;

    /**
     * 账户系统充值完成时间
     */
    private Date accountStatusTime;

    /**
     * 账户系统充值状态描述
     */
    private String accountStatusDesc;

    /**
     * 其他金额
     */
    private BigDecimal otherAmount;

    /**
     * 其他数量
     */
    private BigDecimal otherQuantity;

    /**
     * 明细状态(0进行中1完成2取消)
     */
    private Integer detailsStatus;

    /**
     * 充值订单来源(BANK_CARD, RECHARGE)
     */
    private String orderSource;

    /**
     * 充值使用的卡的id
     */
    private String cardId;

    /**
     * 充值订单来源设备
     */
    private String clientFrom;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 获取流水id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置流水id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取用户Id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取充值法币币种
     */
    public String getFiatCurrency() {
        return fiatCurrency;
    }

    /**
     * 设置充值法币币种
     */
    public void setFiatCurrency(String fiatCurrency) {
        this.fiatCurrency = fiatCurrency;
    }

    /**
     * 获取用户充值金额
     */
    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    /**
     * 设置用户充值金额
     */
    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    /**
     * 获取渠道名称
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置渠道名称
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    /**
     * 获取渠道类型
     */
    public String getChannelType() {
        return channelType;
    }

    /**
     * 设置渠道类型
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    /**
     * 获取渠道业务id
     */
    public String getChannelBizId() {
        return channelBizId;
    }

    /**
     * 设置渠道业务id
     */
    public void setChannelBizId(String channelBizId) {
        this.channelBizId = channelBizId;
    }

    /**
     * 获取渠道充值状态(0成功1失败2.进行中)
     */
    public Integer getChannelRechargeStatus() {
        return channelRechargeStatus;
    }

    /**
     * 设置渠道充值状态(0成功1失败2.进行中)
     */
    public void setChannelRechargeStatus(Integer channelRechargeStatus) {
        this.channelRechargeStatus = channelRechargeStatus;
    }

    /**
     * 获取渠道充值完成时间
     */
    public Date getChannelStatusTime() {
        return channelStatusTime;
    }

    /**
     * 设置渠道充值完成时间
     */
    public void setChannelStatusTime(Date channelStatusTime) {
        this.channelStatusTime = channelStatusTime;
    }

    /**
     * 获取渠道状态描述
     */
    public String getChannelStatusDesc() {
        return channelStatusDesc;
    }

    /**
     * 设置渠道状态描述
     */
    public void setChannelStatusDesc(String channelStatusDesc) {
        this.channelStatusDesc = channelStatusDesc;
    }

    /**
     * 获取渠道手续费类型
     */
    public String getChannelFeeTag() {
        return channelFeeTag;
    }

    /**
     * 设置渠道手续费类型
     */
    public void setChannelFeeTag(String channelFeeTag) {
        this.channelFeeTag = channelFeeTag;
    }

    /**
     * 获取渠道手续费
     */
    public BigDecimal getChannelFeeAmount() {
        return channelFeeAmount;
    }

    /**
     * 设置渠道手续费
     */
    public void setChannelFeeAmount(BigDecimal channelFeeAmount) {
        this.channelFeeAmount = channelFeeAmount;
    }

    /**
     * 获取渠道确认充值金额
     */
    public BigDecimal getChannelRechargebackAmount() {
        return channelRechargebackAmount;
    }

    /**
     * 设置渠道确认充值金额
     */
    public void setChannelRechargebackAmount(BigDecimal channelRechargebackAmount) {
        this.channelRechargebackAmount = channelRechargebackAmount;
    }

    /**
     * 获取平台手续费类型
     */
    public String getPlatformFeeTag() {
        return platformFeeTag;
    }

    /**
     * 设置平台手续费类型
     */
    public void setPlatformFeeTag(String platformFeeTag) {
        this.platformFeeTag = platformFeeTag;
    }

    /**
     * 获取平台手续费
     */
    public BigDecimal getPlatformFeeAmount() {
        return platformFeeAmount;
    }

    /**
     * 设置平台手续费
     */
    public void setPlatformFeeAmount(BigDecimal platformFeeAmount) {
        this.platformFeeAmount = platformFeeAmount;
    }

    /**
     * 获取账户系统业务id
     */
    public String getAccountBizId() {
        return accountBizId;
    }

    /**
     * 设置账户系统业务id
     */
    public void setAccountBizId(String accountBizId) {
        this.accountBizId = accountBizId;
    }

    /**
     * 获取充值后账户可用余额
     */
    public BigDecimal getAccountUserBalance() {
        return accountUserBalance;
    }

    /**
     * 设置充值后账户可用余额
     */
    public void setAccountUserBalance(BigDecimal accountUserBalance) {
        this.accountUserBalance = accountUserBalance;
    }

    /**
     * 获取账户系统确认充值金额
     */
    public BigDecimal getAccountRechargebackAmount() {
        return accountRechargebackAmount;
    }

    /**
     * 设置账户系统确认充值金额
     */
    public void setAccountRechargebackAmount(BigDecimal accountRechargebackAmount) {
        this.accountRechargebackAmount = accountRechargebackAmount;
    }

    /**
     * 获取账户系统充值状态(0成功1失败)
     */
    public Integer getAccountRechargeStatus() {
        return accountRechargeStatus;
    }

    /**
     * 设置账户系统充值状态(0成功1失败)
     */
    public void setAccountRechargeStatus(Integer accountRechargeStatus) {
        this.accountRechargeStatus = accountRechargeStatus;
    }

    /**
     * 获取账户系统充值完成时间
     */
    public Date getAccountStatusTime() {
        return accountStatusTime;
    }

    /**
     * 设置账户系统充值完成时间
     */
    public void setAccountStatusTime(Date accountStatusTime) {
        this.accountStatusTime = accountStatusTime;
    }

    /**
     * 获取账户系统充值状态描述
     */
    public String getAccountStatusDesc() {
        return accountStatusDesc;
    }

    /**
     * 设置账户系统充值状态描述
     */
    public void setAccountStatusDesc(String accountStatusDesc) {
        this.accountStatusDesc = accountStatusDesc;
    }

    /**
     * 获取其他金额
     */
    public BigDecimal getOtherAmount() {
        return otherAmount;
    }

    /**
     * 设置其他金额
     */
    public void setOtherAmount(BigDecimal otherAmount) {
        this.otherAmount = otherAmount;
    }

    /**
     * 获取其他数量
     */
    public BigDecimal getOtherQuantity() {
        return otherQuantity;
    }

    /**
     * 设置其他数量
     */
    public void setOtherQuantity(BigDecimal otherQuantity) {
        this.otherQuantity = otherQuantity;
    }

    /**
     * 获取明细状态(0进行中1完成2取消)
     */
    public Integer getDetailsStatus() {
        return detailsStatus;
    }

    /**
     * 设置明细状态(0进行中1完成2取消)
     */
    public void setDetailsStatus(Integer detailsStatus) {
        this.detailsStatus = detailsStatus;
    }

    /**
     * 获取充值订单来源(BANK_CARD, RECHARGE)
     */
    public String getOrderSource() {
        return orderSource;
    }

    /**
     * 设置充值订单来源(BANK_CARD, RECHARGE)
     */
    public void setOrderSource(String orderSource) {
        this.orderSource = orderSource;
    }

    /**
     * 获取充值使用的卡的id
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 设置充值使用的卡的id
     */
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    /**
     * 获取充值订单来源设备
     */
    public String getClientFrom() {
        return clientFrom;
    }

    /**
     * 设置充值订单来源设备
     */
    public void setClientFrom(String clientFrom) {
        this.clientFrom = clientFrom;
    }

    /**
     * 获取创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取更新时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置更新时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", userId=").append(userId);
        sb.append(", fiatCurrency=").append(fiatCurrency);
        sb.append(", rechargeAmount=").append(rechargeAmount);
        sb.append(", channelName=").append(channelName);
        sb.append(", channelType=").append(channelType);
        sb.append(", channelBizId=").append(channelBizId);
        sb.append(", channelRechargeStatus=").append(channelRechargeStatus);
        sb.append(", channelStatusTime=").append(channelStatusTime);
        sb.append(", channelStatusDesc=").append(channelStatusDesc);
        sb.append(", channelFeeTag=").append(channelFeeTag);
        sb.append(", channelFeeAmount=").append(channelFeeAmount);
        sb.append(", channelRechargebackAmount=").append(channelRechargebackAmount);
        sb.append(", platformFeeTag=").append(platformFeeTag);
        sb.append(", platformFeeAmount=").append(platformFeeAmount);
        sb.append(", accountBizId=").append(accountBizId);
        sb.append(", accountUserBalance=").append(accountUserBalance);
        sb.append(", accountRechargebackAmount=").append(accountRechargebackAmount);
        sb.append(", accountRechargeStatus=").append(accountRechargeStatus);
        sb.append(", accountStatusTime=").append(accountStatusTime);
        sb.append(", accountStatusDesc=").append(accountStatusDesc);
        sb.append(", otherAmount=").append(otherAmount);
        sb.append(", otherQuantity=").append(otherQuantity);
        sb.append(", detailsStatus=").append(detailsStatus);
        sb.append(", orderSource=").append(orderSource);
        sb.append(", cardId=").append(cardId);
        sb.append(", clientFrom=").append(clientFrom);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append("]");
        return sb.toString();
    }
}