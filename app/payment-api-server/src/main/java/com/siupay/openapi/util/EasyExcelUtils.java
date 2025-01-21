package com.siupay.openapi.util;

import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import com.siupay.common.api.dto.response.BasePaginationResponse;
import org.apache.commons.collections.CollectionUtils;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import static com.siupay.openapi.util.Constants.PAYOUT_DOWNLOAD_SHEETNAME;

/**
 * @program: deposit
 * @description: 导出倒入
 * @author: Sandy
 **/
@UtilityClass
@Slf4j
public class EasyExcelUtils {

    /**
     * 重复分页查询数据多次写入excel
     */
    public static <T, S> void write(DataCommand<T, S> command, Class<T> bo, S s,
                                    HttpServletResponse httpServletResponse) {
        ExcelWriter excelWriter = null;
        try {
            // 指定输出流
            excelWriter = EasyExcel.write(httpServletResponse.getOutputStream(), bo).build();
            // 指定sheet
            WriteSheet writeSheet = EasyExcel.writerSheet(PAYOUT_DOWNLOAD_SHEETNAME).build();
            int page = 0;
            BasePaginationResponse<T> pages;
            do {
                page++;
                pages = command.execute(s, page);
                excelWriter.write(pages.getItems(), writeSheet);
            } while (CollectionUtils.isNotEmpty(pages.getItems()) && page < pages.getTotalPage());
        } catch (Exception ex) {
            log.error("导出后端订单列表失败！请重试！", ex);
        } finally {
            if (Objects.nonNull(excelWriter)) {
                excelWriter.finish();
            }
        }
    }

    @FunctionalInterface
    public interface DataCommand<T, S> {
        BasePaginationResponse<T> execute(S s, int page);
    }
}
