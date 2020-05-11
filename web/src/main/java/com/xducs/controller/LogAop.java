package com.xducs.controller;

import com.xducs.domain.SysLog;
import com.xducs.service.ISysLogService;
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
 * @author Linbo Ge
 * @date 2020/5/10 - 19:41
 */
@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;
    //开始时间
    private Date visitTime;
    //访问的类
    private Class aClass;
    //访问的方法
    private Method method;

    //前置通知 获取开启时间，执行类名，方法名
    @Before("execution(* com.xducs.controller.*.*(..))")
    public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException {
        //当前时间就是访问时间
        visitTime = new Date();
        //具体要访问的类
        aClass = joinPoint.getTarget().getClass();
        //访问的方法名
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        //获取执行的方法
        if (args == null || args.length == 0) {
            //只能获取无参数的方法
            method = aClass.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            aClass.getMethod(methodName, classArgs);
        }
    }

    //后置通知
    @After("execution(* com.xducs.controller.*.*(..))")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        //获取响应时长
        long time = System.currentTimeMillis() - visitTime.getTime();
        String url = "";
        //获取url
        if (aClass != null && method != null && aClass != LogAop.class) {
            //1.获取类上@RequestMapping("/orders")注解
            RequestMapping classAnnotation = (RequestMapping) aClass.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的value注解
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                    if (!url.equals("/sysLog/findAll")) {
                        //获取访问ip
                        String ip = request.getRemoteAddr();
                        //获取当前操作用户
                        SecurityContext context = SecurityContextHolder.getContext();//从上下文获取当前登录用户
                        User user = (User) context.getAuthentication().getPrincipal();
                        String username = user.getUsername();
                        //将日志相关信息封装
                        SysLog sysLog = new SysLog();
                        sysLog.setExecutionTime(time);
                        sysLog.setIp(ip);
                        sysLog.setMethod("[类名] " + aClass.getName() + "[方法名]" + method.getName());
                        sysLog.setUrl(url);
                        sysLog.setUsername(username);
                        sysLog.setVisitTime(visitTime);

                        //调用service完成操作
                        sysLogService.save(sysLog);
                    }

                }
            }
        }

    }
}