package com.arthur.aop;

import org.springframework.stereotype.Service;

/**
 * Created by wangtao on 17/3/20.
 */
@Service
public class PerformanceImpl implements Performance {
    @Override
    public void perform() {
        System.out.printf("I am start study spring Aop! \n");
    }
}
