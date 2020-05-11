package com.xducs.service.impl;

import com.github.pagehelper.PageHelper;
import com.xducs.dao.IOrdersDao;
import com.xducs.domain.Orders;
import com.xducs.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/4 - 15:36
 */
@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page, int size) throws Exception {
        //分页查询
        PageHelper.startPage(page, size);
        return ordersDao.findAll();
    }

    @Override
    public Orders findById(Integer ordersId) throws Exception {
        return ordersDao.findById(ordersId);
    }
}
