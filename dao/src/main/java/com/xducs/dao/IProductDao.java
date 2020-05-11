package com.xducs.dao;

import com.xducs.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/2 - 16:55
 */
public interface IProductDao {
    /**
     * 查询所有商品
     *
     * @return
     * @throws Exception
     */
    @Select("select * from product")
    List<Product> findAll() throws Exception;

    /**
     * 根据id查询
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from product where id = #{id}")
    Product findById(Integer id) throws Exception;

    /**
     * 添加商品
     *
     * @param product
     */
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
