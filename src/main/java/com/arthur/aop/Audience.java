package com.arthur.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


/**
 * Created by wangtao on 17/3/20.
 */
@Aspect
public class Audience {

   // @Pointcut("execution(** Performance.perform(..))")
    //public void performance;


    @Before("execution(public * com.arthur.aop.Performance.*(..))")
    public void silenceCellPhoens(){
        System.out.printf("call phones \n");
    }
}
