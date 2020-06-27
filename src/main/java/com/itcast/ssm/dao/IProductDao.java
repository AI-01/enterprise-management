package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/23 - 19:54
 */
@Repository
public interface IProductDao  {

    //查询所有产品信息
    @Select("select * from product")
    public List<Product> findAll();

    @Select("select * from product where id=#{id}")
    Product findById(String id);

    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);
}
