package com.siupay.openapi.v1.persistence.mapper;

import com.siupay.openapi.v1.persistence.entity.RechargeOrderDetailsDO;
import com.siupay.openapi.v1.persistence.entity.RechargeOrderDetailsDOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RechargeOrderDetailsMapper {
    /**
     * insert one record into table, ignore nullable value, 
     */
    int insertSelective(RechargeOrderDetailsDO record);

    /**
     * update one record by primary key, ignore nullable value
     */
    int updateByPrimaryKeySelective(RechargeOrderDetailsDO record);

    /**
     * update record by example, ignore nullable value
     */
    int updateByExampleSelective(@Param("record") RechargeOrderDetailsDO record, @Param("example") RechargeOrderDetailsDOExample example);

    /**
     * delete one record by primary key 
     */
    int deleteByPrimaryKey(String id);

    /**
     * delete record by example
     */
    int deleteByExample(RechargeOrderDetailsDOExample example);

    /**
     * get one record by primary key
     */
    RechargeOrderDetailsDO selectByPrimaryKey(String id);

    /**
     * get record by example
     */
    List<RechargeOrderDetailsDO> selectByExample(RechargeOrderDetailsDOExample example);

    /**
     * get one record by example
     */
    RechargeOrderDetailsDO selectOneByExample(RechargeOrderDetailsDOExample example);

    /**
     * select count(*) from table by example
     */
    long countByExample(RechargeOrderDetailsDOExample example);

    /**
     * batch insert  record into table
     */
    int batchInsert(@Param("items") List<RechargeOrderDetailsDO> items);
}