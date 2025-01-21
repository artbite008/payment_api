package com.siupay.openapi.util;

import lombok.experimental.UtilityClass;

/**
 * @program: payment-api
 * @description:
 * @author: Sandy
 **/
@UtilityClass
public class Constants {
    /**
     * 格式化类型
     */
    public static final String DEFAULT_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /**
     * format
     */
    public static final String FORMAT_ENCODING = "UTF-8";

    /**
     * 下载前缀
     */
    public static final String ADMIN_DOWNLOAD = "ADMIN_DOWNLOAD";

    /**
     * 下载后缀 Suffix
     */
    public static final String DOWNLOAD_SUFFIX = ".xlsx";

    /**
     * CONTENT
     */
    public static final String DOWNLOAD_CONTENT_TYPE = "application/octet-stream;charset=UTF-8";

    /**
     * ENCODING
     */
    public static final String DOWNLOAD_ENCODING = "utf8";

    /**
     * DISPOSITION
     */
    public static final String DOWNLOAD_DISPOSITION = "Content-Disposition";

    /**
     * DOWNLOAD_ATTACHMENT
     */
    public static final String DOWNLOAD_ATTACHMENT = "attachment; filename=";

    /**
     * 订单历史记录下载每页默认条数
     */
    public static final Integer DOWNLOAD_PAGE_SIZE = 100;

    /**
     * date format yyyy-MM
     */
    public static final String DATE_FORMAT_YYYY_MM = "yyyy-MM";

    /**
     * sheetName
     */
    public static final String PAYOUT_DOWNLOAD_SHEETNAME = "payout";
}
