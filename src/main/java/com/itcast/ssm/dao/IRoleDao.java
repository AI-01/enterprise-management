package com.itcast.ssm.dao;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/24 - 21:23
 */
@Repository
public interface IRoleDao {

    @Select("SELECT * FROM role WHERE id IN(SELECT roleId FROM users_role WHERE userId = #{uid})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.itcast.ssm.dao.IPermissionDao.findPermissionByRoleId")),
    })
    List<Role> findRoleByUserId(String uid);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id=#{rid}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,many = @Many(select = "com.itcast.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    Role findById(String rid);

    @Delete("delete from users_role where roleId=#{rid}")
    void deleteUsersRoleById(String rid);

    @Delete("delete from role_permission where roleId=#{rid}")
    void deleteRolePermissionById(String rid);

    @Delete("delete from role where id=#{rid}")
    void deleteRoleById(String rid);

    @Select("SELECT * FROM permission WHERE id NOT IN(SELECT permissionId FROM role_permission WHERE roleId=#{rid})")
    List<Permission> findOtherPermission(String rid);

    @Insert("insert into role_permission(permissionId,roleId) values(#{pid},#{rid})")
    void addPermissionToRole(@Param("rid") String rid, @Param("pid") String pid);
}
