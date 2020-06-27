package com.itcast.ssm.web;

import com.itcast.ssm.domain.Role;
import com.itcast.ssm.domain.UserInfo;
import com.itcast.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/25 - 10:27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mav.addObject("userList",userList);
        mav.setViewName("user-list");
        return mav;
    }

    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String uid){
        ModelAndView mav = new ModelAndView();
        UserInfo user = userService.findById(uid);
        mav.addObject("user",user);
        mav.setViewName("user-show");
        return mav;
    }

    //根据uid查询用户和该用户可以添加的角色
    @RequestMapping("/findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String uid){
        ModelAndView mav = new ModelAndView();
        UserInfo user = userService.findById(uid);
        List<Role> roleList = userService.findOtherRole(uid);
        mav.addObject("user",user);
        mav.addObject("roleList",roleList);
        mav.setViewName("user-role-add");
        return mav;
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String uid,
                                @RequestParam(name = "ids",required = true) String[] rids){
        userService.addRoleToUser(uid,rids);
        return "redirect:findAll";
    }
}
