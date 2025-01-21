/*
 * ```
 * Copyright 2019 Mek Global Limited
 * ```
 */


package com.siupay.openapi.util.utils;

import com.siupay.common.api.exception.ErrorCode;
import com.siupay.common.api.exception.PaymentException;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ab on 2018/9/7
 */
public class BeanConvert {
    public static <T> T convert(Object src, Class<T> claszz) {
        try {
            if (src == null) {
                return null;
            }

            T t = claszz.newInstance();

            copyProperties(src, t);

            return t;
        } catch (Exception e) {
            throw new PaymentException(ErrorCode.SERVER_ERROR);
        }
    }

    public static <E, T> List<T> multiConvert(List<E> objects, Class<T> clazz) {
        if (CollectionUtils.isEmpty(objects)) {
            return new ArrayList<>();
        }
        return objects.stream()
                .map(item -> convert(item, clazz))
                .collect(Collectors.toList());
    }

    public static Map<String, Object> convertBeanToMap(Object bean) {
        Assert.isNull(bean, "bean is null.");

        try {
            Class<? extends Object> type = bean.getClass();
            Map<String, Object> returnMap = new HashMap();
            BeanInfo beanInfo = Introspector.getBeanInfo(type);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            PropertyDescriptor descriptor = null;
            String propertyName = null;
            Method readMethod = null;
            Object result = null;

            for (int i = 0; i < propertyDescriptors.length; i++) {
                descriptor = propertyDescriptors[i];
                propertyName = descriptor.getName();

                if (!propertyName.equals("class")) {
                    readMethod = descriptor.getReadMethod();

                    if (null != readMethod) {
                        result = readMethod.invoke(bean, new Object[0]);
                        returnMap.put(propertyName, result);
                    }
                }
            }

            return returnMap;
        } catch (Exception e) {
            throw new PaymentException(ErrorCode.SERVER_ERROR);
        }
    }

    public static MultiValueMap convertToMultiValueMap(Object bean) {
        Map<String, Object> temp = convertBeanToMap(bean);
        MultiValueMap<String, Object> result = new LinkedMultiValueMap<String, Object>();

        temp.forEach(
                (key, val) -> {
                    if (val != null) {
                        result.add(key, String.valueOf(val));
                    }
                });

        return result;
    }

    public static void setMapVal(String key, Map<String, String> val, MultiValueMap<String, Object> params) {
        val.forEach((key1, val1) -> {
            if (val1 != null) {
                params.add(key + "[" + key1 + "]", val1);
            }
        });

    }

    public static void copyProperties(Object src, Object tag) {
        copyProperties(src, tag, null);
    }

    public static void copyProperties(Object src, Object tag, String... ignoreProperties) {
        BeanUtils.copyProperties(src, tag, ignoreProperties);
    }

}



