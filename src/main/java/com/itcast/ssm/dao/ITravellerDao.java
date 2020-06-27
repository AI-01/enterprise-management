package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/24 - 16:57
 */
@Repository
public interface ITravellerDao {

    @Select("SELECT * FROM traveller WHERE id IN(SELECT travellerId FROM order_traveller WHERE orderId=#{oid})")
    List<Traveller> findByOrdersId(String oid);

}
