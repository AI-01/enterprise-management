package com.itcast.ssm.service.impl;

import com.itcast.ssm.dao.IUserDao;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.domain.UserInfo;
import com.itcast.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin
 * @create 2020/3/24 - 19:41
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        //将数据库查询出来的对象包装成Spring提供的User对象
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));
       //密码未加密，spring-security配置文件中将密码加密方式注释掉
//        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),userInfo.getStatus() == 0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        //密码加密，spring-security配置文件中配置密码加密方式
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        //对密码进行加密
        String encodePassword = bCryptPasswordEncoder.encode(userInfo.getPassword());
        userInfo.setPassword(encodePassword);
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String uid) {
        return userDao.findById(uid);
    }

    @Override
    public List<Role> findOtherRole(String uid) {
        return userDao.findOtherRole(uid);
    }

    @Override
    public void addRoleToUser(String uid, String[] rids) {
        for (String rid : rids) {
            userDao.addRoleToUser(uid,rid);
        }
    }
}
