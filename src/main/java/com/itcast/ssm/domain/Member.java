package com.itcast.ssm.domain;

/**
 * @author Lin
 * @create 2020/3/24 - 10:53
 */
public class Member {
    private String id;  //主键
    private String name;    //姓名
    private String nickname;    //昵称
    private String phoneNum;    //电话号码
    private String email;   //邮箱

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}