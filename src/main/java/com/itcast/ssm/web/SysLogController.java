package com.itcast.ssm.web;

import com.itcast.ssm.domain.SysLog;
import com.itcast.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Lin
 * @create 2020/3/27 - 11:27
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mav = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAll();
        mav.addObject("sysLogList",sysLogList);
        mav.setViewName("syslog-list");
        return mav;
    }
}
