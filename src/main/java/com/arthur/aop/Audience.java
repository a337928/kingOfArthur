package com.arthur.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * Created by wangtao on 17/3/20.
 */
@Aspect
@Component
public class Audience {

   // @Pointcut("execution(** Performance.perform(..))")
    //public void performance;


    @Before("execution(public * com.arthur.action.CommonAction.*(..))")
    public void silenceCellPhoens(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.printf("URL : " + request.getRequestURL().toString()+"\n");
        System.out.printf("HTTP_METHOD : " + request.getMethod()+"\n");
        System.out.printf("IP : " + request.getRemoteAddr()+"\n");
        System.out.printf("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()+"\n");
        System.out.printf("ARGS : " + Arrays.toString(joinPoint.getArgs())+"\n");

    }
}
