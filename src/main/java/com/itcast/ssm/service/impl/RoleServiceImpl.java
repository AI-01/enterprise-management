package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.IRoleDao;
import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/25 - 16:24
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(String rid) {
        return roleDao.findById(rid);
    }

    @Override
    public void deleteRoleById(String rid) {
        roleDao.deleteUsersRoleById(rid);
        roleDao.deleteRolePermissionById(rid);
        roleDao.deleteRoleById(rid);
    }

    @Override
    public List<Permission> findOtherPermission(String rid) {
        return roleDao.findOtherPermission(rid);
    }

    @Override
    public void addPermissionToRole(String rid, String[] pids) {
        for (String pid : pids) {
            roleDao.addPermissionToRole(rid,pid);
        }
    }
}
