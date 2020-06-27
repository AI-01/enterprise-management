package com.itcast.ssm.domain;

import java.util.List;

/**
 * 对应数据库的users表
 * @author Lin
 * @create 2020/3/24 - 19:54
 */
public class UserInfo {
    private String id;  //主键
    private String username;    //用户名
    private String email;   //邮箱
    private String password;    //密码
    private String phoneNum;    //电话号码
    private int status; // 状态 0未开启 1开启
    private String statusStr;
    private List<Role> roles;   //用户拥有的角色

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusStr() {
        // 状态 0未开启 1开启
        if(status == 0){
            statusStr = "未开启";
        }
        if(status == 1){
            statusStr = "开启";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
