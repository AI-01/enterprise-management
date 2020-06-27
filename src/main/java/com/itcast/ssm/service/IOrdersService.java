package com.itcast.ssm.service;

import com.itcast.ssm.domain.Orders;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/24 - 11:04
 */
public interface IOrdersService {

    List<Orders> findAll();

    List<Orders> findAll(int page, int pageSize);

    Orders findById(String oid);
}
