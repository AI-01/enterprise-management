package com.itcast.ssm.web;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.domain.Role;
import com.itcast.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/25 - 16:23
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<Role> roleList = roleService.findAll();
        mav.addObject("roleList",roleList);
        mav.setViewName("role-list");
        return mav;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String rid){
        ModelAndView mav = new ModelAndView();
        Role role = roleService.findById(rid);
        mav.addObject("role",role);
        mav.setViewName("role-show");
        return mav;
    }

    @RequestMapping("/save")
    @Secured("ROLE_ADMIN")
    public String save(Role role){
        roleService.save(role);
        return "redirect:findAll";
    }

    @RequestMapping("/deleteRoleById")
    @Secured("ROLE_ADMIN")
    public String deleteRoleById(@RequestParam(name = "id",required = true) String rid){
        roleService.deleteRoleById(rid);
        return "redirect:findAll";
    }

    //根据rid查询角色和该角色能添加的所有权限
    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String rid){
        ModelAndView mav = new ModelAndView();
        Role role = roleService.findById(rid);
        List<Permission> permissionList = roleService.findOtherPermission(rid);
        mav.addObject("role",role);
        mav.addObject("permissionList",permissionList);
        mav.setViewName("role-permission-add");
        return mav;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String rid,
                                      @RequestParam(name = "ids",required = true) String[] pids){
        roleService.addPermissionToRole(rid,pids);
        return "redirect:findAll";
    }
}
