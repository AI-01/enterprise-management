package com.itcast.ssm.service;

import com.itcast.ssm.domain.Product;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/23 - 19:56
 */
public interface IProductService {
    //查询所有产品信息
    public List<Product> findAll();

    void save(Product product);

    List<Product> findAll(int page, int pageSize);
}
