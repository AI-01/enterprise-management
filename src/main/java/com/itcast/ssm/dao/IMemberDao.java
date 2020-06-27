package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Lin
 * @create 2020/3/24 - 16:48
 */
@Repository
public interface IMemberDao {

    @Select("select * from member where id=#{mid}")
    Member findById(String mid);
}
