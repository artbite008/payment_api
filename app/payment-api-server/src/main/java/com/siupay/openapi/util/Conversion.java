package com.siupay.openapi.util;

import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @program: deposit
 * @description: bean转换成Map
 * @author: Sandy
 **/
@Slf4j
@UtilityClass
public class Conversion {

    /**
     * 将实体转换为map
     * 
     * @param t
     * @return
     */
    public static <T> Map<String, Object> transBeanToMap(T t) {
        if (Objects.isNull(t)) {
            return MapUtils.EMPTY_MAP;
        }
        Map<String, Object> paramMap = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass());
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            Arrays.asList(descriptors).forEach(descriptor -> {
                String key = descriptor.getName();
                if (!Objects.equals("class", key)) {
                    Method getter = descriptor.getReadMethod();
                    try {
                        Object value = getter.invoke(t);
                        paramMap.put(key, value);
                    } catch (Exception ex) {
                        throw new PaymentException(ErrorCode.SERVER_ERROR);
                    }
                }
            });
        } catch (Exception ex) {
            log.error(String.format("[工具类]-- 实体转换map失败, bean: %s", t.toString()), ex);
        }
        return paramMap;
    }
}
