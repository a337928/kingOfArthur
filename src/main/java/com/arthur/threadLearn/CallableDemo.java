package com.arthur.threadLearn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by wangtao on 17/3/22.
 */
public class CallableDemo {
    public static void  main (String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<String>> list = new ArrayList<Future<String>>();
        for(int i = 0;i < 10;i++)
            list.add(exec.submit(new TaskWithResult(i)));
        for(Future<String> f:list)
            try {
                System.out.println(f.get());
            }catch (InterruptedException e){
                System.out.println(e);

            } catch (ExecutionException e) {
                e.printStackTrace();
                System.out.println(e);

            }finally {
                exec.shutdown();
            }
    }
}
