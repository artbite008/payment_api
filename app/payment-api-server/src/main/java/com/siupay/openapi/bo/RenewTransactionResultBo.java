package com.siupay.openapi.bo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RenewTransactionResultBo {

    /**
     * 匹配的用户id
     */
    private String userId;
    /**
     * 匹配的结果
     */
    private String status;

}
