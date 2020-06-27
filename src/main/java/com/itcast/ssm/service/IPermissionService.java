package com.itcast.ssm.service;

import com.itcast.ssm.domain.Permission;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/25 - 16:55
 */
public interface IPermissionService {

    List<Permission> findAll();

    void save(Permission permission);

    void deletePermissionById(String pid);

    Permission findById(String pid);
}
