package com.itcast.ssm.web;

import com.itcast.ssm.domain.Permission;
import com.itcast.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/25 - 16:54
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        mav.addObject("permissionList",permissionList);
        mav.setViewName("permission-list");
        return mav;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String pid){
        ModelAndView mav = new ModelAndView();
        Permission permission = permissionService.findById(pid);
        mav.addObject("permission",permission);
        mav.setViewName("permission-show");
        return mav;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:findAll";
    }

    @RequestMapping("/deletePermissionById")
    public String deletePermissionById(@RequestParam(name = "id",required = true) String pid){
        permissionService.deletePermissionById(pid);
        return "redirect:findAll";
    }
}
