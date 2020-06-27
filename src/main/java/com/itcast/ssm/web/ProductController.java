package com.itcast.ssm.web;

import com.github.pagehelper.PageInfo;
import com.itcast.ssm.domain.Product;
import com.itcast.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/23 - 20:37
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    //查询所有产品--不使用分页
//    @RequestMapping("/findAll")
//    public ModelAndView findAll(){
//        ModelAndView mav = new ModelAndView();
//        List<Product> productList = productService.findAll();
//        mav.addObject("productList",productList);
//        mav.setViewName("product-list");
//        return mav;
//    }

    //查询所有产品--使用分页
    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1",required = true) Integer page,
                                @RequestParam(name = "pageSize",defaultValue = "5",required = true) Integer pageSize){
        ModelAndView mav = new ModelAndView();
        List<Product> productList = productService.findAll(page,pageSize);
        //将所有产品包装为一个pageBean
        PageInfo pageInfo = new PageInfo(productList);
        mav.addObject("pageInfo",pageInfo);
        mav.setViewName("product-list");
        return mav;
    }

    //添加产品
    @RequestMapping("/add")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll";
    }
}
