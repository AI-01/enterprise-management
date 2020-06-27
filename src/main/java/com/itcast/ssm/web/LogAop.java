package com.itcast.ssm.web;

import com.itcast.ssm.domain.SysLog;
import com.itcast.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author Lin
 * @create 2020/3/26 - 19:56
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request; //注入request请求
    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //访问时间
    private Class clazz;    //访问的类对象
    private Method method;  //访问的方法对象

    @Before("execution(* com.itcast.ssm.web.*.*(..))")
    public void before(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date(); //获取当前访问时间
        clazz = jp.getTarget().getClass();  //获取要访问的类
        String methodName = jp.getSignature().getName();//获取要访问的方法名
        Object[] args = jp.getArgs();   //获取要访问的方法参数
        if (args==null || args.length==0) {
            method = clazz.getMethod(methodName);   //获取无参方法的method对象
        }else{
            Class[] classes = new Class[args.length];
            for (int i = 0; i < classes.length; i++) {
                classes[i] = args[i].getClass();    //获取每个方法参数的类对象
            }
            method = clazz.getMethod(methodName,classes);   //获取带参方法的method对象
        }

    }

    @After("execution(* com.itcast.ssm.web.*.*(..))")
    public void after(JoinPoint jp){
        //将日志信息封装到SysLog类中
        SysLog sysLog = new SysLog();
        sysLog.setVisitTime(visitTime);

        //获取访问的时长
        long executionTime = new Date().getTime()-visitTime.getTime();
        sysLog.setExecutionTime(executionTime);

        //获取访问资源url
        String url = "";
        if(clazz!=null && method!=null && clazz!=LogAop.class){
            //获取类上的@RequestMapping("/xxx)路径
            RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(clazzAnnotation != null){
                String[] clazzUrls = clazzAnnotation.value();
                //获取方法上的@RequestMapping("/xxx)路径
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodUrls = methodAnnotation.value();
                    url = clazzUrls[0]+methodUrls[0];
                    sysLog.setUrl(url);
                    sysLog.setMethod(clazz.getName()+"/" +method.getName());
                }
            }
        }
        //获取访问ip
        String ip = request.getRemoteAddr();
        sysLog.setIp(ip);

        //获取当前的访问用户
        SecurityContext context = SecurityContextHolder.getContext();//获取当前登录环境上下文
        User user = (User) context.getAuthentication().getPrincipal();//通过登录上下文获取登录用户
        String username = user.getUsername();//获取登录用户的用户名
        sysLog.setUsername(username);

        //调用service，保存日志信息
        sysLogService.save(sysLog);
    }
}
