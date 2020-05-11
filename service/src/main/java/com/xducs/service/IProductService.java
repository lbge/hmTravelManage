package com.xducs.service;

import com.xducs.domain.Product;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/2 - 16:52
 */
public interface IProductService {
    /**
     * 查询所有商品
     *
     * @return
     * @throws Exception
     */
    List<Product> findAll() throws Exception;

    /**
     * 添加商品
     * @param product
     */
    void save(Product product) throws Exception;
}
