package com.itcast.ssm.domain;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/24 - 19:54
 */
public class Role {
    private String id;  //主键
    private String roleName;    //角色名
    private String roleDesc;    //角色描述
    private List<Permission> permissions;   //角色具有的权限
    private List<UserInfo> users;   //角色赋予的用户

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}
