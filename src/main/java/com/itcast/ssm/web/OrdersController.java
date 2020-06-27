package com.itcast.ssm.web;

import com.github.pagehelper.PageInfo;
import com.itcast.ssm.domain.Orders;
import com.itcast.ssm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author Lin
 * @create 2020/3/24 - 11:06
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //查询所有订单--没有使用分页
    /*
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll();
        mav.addObject("ordersList",ordersList);
        mav.setViewName("orders-list");
        return mav;
    }
    */
    //查询所有订单--使用分页
    @RequestMapping("/findAll")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer pageSize){
        ModelAndView mav = new ModelAndView();
        List<Orders> ordersList = ordersService.findAll(page, pageSize);
        //将所有订单包装为一个pageBean
        PageInfo pageInfo = new PageInfo(ordersList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("orders-list");
        return mav;
    }

    //根据id查询订单
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String oid){
        ModelAndView mav = new ModelAndView();
        Orders orders = ordersService.findById(oid);
        mav.addObject("orders",orders);
        mav.setViewName("orders-show");
        return mav;
    }
}
