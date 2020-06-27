package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/25 - 12:36
 */
@Repository
public interface IPermissionDao {

    @Select("SELECT * FROM permission WHERE id IN(SELECT permissionId FROM role_permission WHERE roleId=#{rid})")
    List<Permission> findPermissionByRoleId(String rid);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);

    @Delete("delete from role_permission where permissionId=#{pid}")
    void deleteRolePermissionById(String pid);

    @Delete("delete from permission where id=#{pid}")
    void deletePermissionById(String pid);

    @Select("select * from permission where id=#{pid}")
    Permission findById(String pid);
}
