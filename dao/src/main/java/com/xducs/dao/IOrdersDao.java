package com.xducs.dao;

import com.xducs.domain.Member;
import com.xducs.domain.Orders;
import com.xducs.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Linbo Ge
 * @date 2020/5/4 - 15:36
 */
public interface IOrdersDao {
    /**
     * 查询所有订单
     *
     * @return
     * @throws Exception
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "com.xducs.dao.IProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    /**
     * 查询订单详情
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from orders where id = #{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "com.xducs.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId", javaType = Member.class,
                    one = @One(select = "com.xducs.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.xducs.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(Integer ordersId) throws Exception;
}
