package com.xducs.dao;

import com.xducs.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/7 - 13:44
 */
public interface ITravellerDao {
    @Select("select * from traveller where id in (select travellerId from orders_traveller where orderId=#{ordersId})")
    List<Traveller> findByOrdersId(Integer ordersId) throws Exception;
}
