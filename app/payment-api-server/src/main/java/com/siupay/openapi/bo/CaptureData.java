package com.siupay.openapi.bo;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;

@NotThreadSafe
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class CaptureData {

    // 事件的行为的全局唯一标示。如：当同一用户行为重复上报时，该字段取值不变。
    protected String id = StringUtils.EMPTY;

    // 一类行为的事件的唯一标示。如，登陆事件，不同的用户的登陆行为，该字段取值不变。
    @JSONField(name="c")
    protected long code = 0;

    // 行为事件元数据的对应版本
    @JSONField(name="sv")
    protected String version;

    // 事件行为发生时间
    @JSONField(name="ts")
    protected long timestamp = 0;

    // 终端类型
    @JSONField(name="ct")
    protected String clientType;

    // 用户在指定终端下的标示
    @JSONField(name="cid")
    protected String clientId = StringUtils.EMPTY;

    // 用户注册后的全局唯一标示
    @JSONField(name="uid")
    protected String userId;

    // 用户终端硬件设备类型
    @JSONField(name="d")
    protected String deviceType = StringUtils.EMPTY;

    // 用户终端的IP地址
    protected String ip;

    // 用户终端agent信息
    @JSONField(name="ua")
    protected String userAgent;

    // 事件的业务信息
    protected Map<String, Object> data = Collections.emptyMap();

    @Nonnull
    public String getId() {
        return id;
    }

    public void setId( String id) {
        this.id = Objects.requireNonNull(id);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    @Nonnull
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Nonnull
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(@Nonnull String deviceType) {
        this.deviceType = Objects.requireNonNull(deviceType);
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Nonnull
    public Map<String, Object> getData() {
        return data;
    }

    public void setData(@Nonnull Map<String, Object> data) {
        this.data = Objects.requireNonNull(data);
    }


}

