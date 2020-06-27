package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Member;
import com.itcast.ssm.domain.Orders;
import com.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/24 - 11:02
 */
@Repository
public interface IOrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.itcast.ssm.dao.IProductDao.findById")),
    })
    List<Orders> findAll();

    @Select("select * from orders where id=#{oid}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId",javaType = Product.class,one = @One(select = "com.itcast.ssm.dao.IProductDao.findById")),
            @Result(property = "member", column = "memberId",javaType = Member.class,one = @One(select = "com.itcast.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers", column = "id",javaType = List.class,many = @Many(select = "com.itcast.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    Orders findById(String oid);
}
