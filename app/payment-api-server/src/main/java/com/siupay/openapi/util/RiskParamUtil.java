package com.siupay.openapi.util;

import com.siupay.openapi.v1.constants.RequestExtFields;
import com.siupay.starter.chaincontext.ChainContextConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * @author: Uther Chen
 * @description:
 * @create: 2022/5/30 14:03
 **/

@Slf4j
public class RiskParamUtil {
    public static void putRiskExtra(Map<String, Object> ext) {
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            if (Objects.isNull(requestAttributes)) {
                log.warn("非http请求，无法提取风控参数");
                return;
            }

            HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
            if (Objects.isNull(ext.get(RequestExtFields.FINGER_ID))) {
                String fingerId = httpServletRequest.getHeader(ChainContextConstants.FINGER_ID);
                ext.put(RequestExtFields.FINGER_ID, fingerId);
            }
            if (Objects.isNull(ext.get(RequestExtFields.FINGER_PID))) {
                String fingerPId = httpServletRequest.getHeader(ChainContextConstants.FINGER_PID);
                ext.put(RequestExtFields.FINGER_PID, fingerPId);
            }
            if (Objects.isNull(ext.get(RequestExtFields.DEVICE_ID))) {
                String deviceId = httpServletRequest.getHeader(ChainContextConstants.DEVICE_ID);
                ext.put(RequestExtFields.DEVICE_ID, deviceId);
            }
        } catch (Exception e) {
            log.error("提取风控的参数异常", e);
        }
    }
}
