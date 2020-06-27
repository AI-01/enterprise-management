package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/25 - 16:24
 */
public interface IRoleService {
    List<Role> findAll();

    void save(Role role);

    Role findById(String rid);

    void deleteRoleById(String rid);

    List<Permission> findOtherPermission(String rid);

    void addPermissionToRole(String rid, String[] pids);
}
