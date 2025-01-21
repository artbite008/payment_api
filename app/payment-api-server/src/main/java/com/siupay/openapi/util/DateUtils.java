package com.siupay.openapi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.joda.time.DateTime;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static com.siupay.openapi.util.Constants.DATE_FORMAT_YYYY_MM;
import static com.siupay.openapi.util.Constants.DEFAULT_FORMAT_PATTERN;

/**
 * @program: deposit
 * @description:
 * @author: Sandy
 * @create: 2021-06-16
 **/
@Slf4j
@UtilityClass
public class DateUtils {

    /**
     * 根据条件获取当前时间前段区间时间
     *
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date getTime(DateTime dateTime, Integer hour, Integer minute, Integer second) {
        return getTime(dateTime, null, hour, minute, second);
    }

    public static Date getTimeByMinute(DateTime dateTime, Integer minute) {
        return getTime(dateTime, null, minute, null);
    }

    public static Date getDayByMinute(DateTime dateTime, Integer day) {
        return getTime(dateTime, day, null, null, null);
    }

    public static Date getTime(DateTime dateTime, Integer day, Integer hour, Integer minute, Integer second) {

        if (Objects.nonNull(day)) {
            dateTime = dateTime.minusDays(day);
        }
        if (!Objects.isNull(hour)) {
            dateTime = dateTime.minusHours(hour);
        }
        if (!Objects.isNull(minute)) {
            dateTime = dateTime.minusMinutes(minute);
        }
        if (!Objects.isNull(second)) {
            dateTime = dateTime.minusSeconds(second);
        }
        return dateTime.toDate();
    }

    /**
     * 根据参数比较年月
     * 
     * @param dateTime
     * @param year
     * @param month
     * @return
     */
    public static Boolean compareJoiningDate(DateTime dateTime, Integer year, Integer month) {
        Boolean expire = Boolean.FALSE;
        try {
            // 获取失效时间
            Date expireDate = formatDate(year, month);
            expire = expireDate.compareTo(dateTime.toDate()) > 0;
        } catch (Exception ex) {
            log.error(String.format("解析失效时间失败, year: %s, month: %s", year, month), ex);
        }
        return expire;
    }

    private static Date formatDate(Integer year, Integer month) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YYYY_MM);
        Date expireDate = format.parse(String.format("%s-%s", year, month));
        Integer expireDays = getDaysOfMonth(expireDate);
        SimpleDateFormat formatExpire = new SimpleDateFormat(DEFAULT_FORMAT_PATTERN);
        return formatExpire.parse(String.format("%s-%s-%s 23:59:59", year, month, expireDays));
    }

    private static Integer getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 格式化时间
     *
     * @param date
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat defaultFormat = new SimpleDateFormat(pattern);
        return defaultFormat.format(date);
    }
}
