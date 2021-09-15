package com.arthur.threadLearn;

import java.util.concurrent.Callable;

/**
 * Created by wangtao on 17/3/22.
 */
public class TaskWithResult implements Callable<String>{
    private  int id;
    public TaskWithResult(int id){
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        if ((id % 2) == 0){
            Thread.sleep(410);
        }
        return "result I am id is " + id;
    }



}


