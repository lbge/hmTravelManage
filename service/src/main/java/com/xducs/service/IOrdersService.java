package com.xducs.service;

import com.xducs.domain.Orders;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/4 - 15:35
 */
public interface IOrdersService {
    /**
     * 查询所有订单
     * @return
     * @throws Exception
     */
    List<Orders> findAll(int page,int size) throws Exception;

    Orders findById(Integer ordersId) throws Exception;
}
