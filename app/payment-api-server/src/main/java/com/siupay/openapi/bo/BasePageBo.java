package com.siupay.openapi.bo;


import lombok.Data;

@Data
public class BasePageBo {

    /**
     * 当前页
     */
    private int pageNum = 1;

    /**
     * 每页展示数量
     */
    private int pageSize = 10;

}
