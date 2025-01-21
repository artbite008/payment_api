package com.siupay.openapi.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;

/**
 * 处理前后空位工具类
 */
@UtilityClass
public class TrimUtils {

    /**
     * 去除Map中字符串的前后空位
     * 
     * @param data
     */
    public static void trimMap(Map data) {
        if (!MapUtils.isEmpty(data)) {
            Iterator it = data.keySet().iterator();
            while (it.hasNext()) {
                Object key = it.next();
                Object value = data.get(key);
                if (Objects.nonNull(value) && value instanceof String) {
                    data.put(key, StringUtils.trim((String) value));
                }
            }
        }
    }

}
