package com.arthur.threadLearn.impl;

import com.arthur.threadLearn.IThread;
import org.springframework.stereotype.Service;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangtao on 17/3/18.
 */
@Service
public class ThreadImpl implements IThread {

    private String runnableName = "runnableName";
    private static int poolSize = 5;
    private static int maximum = 10;
    private static long keepAlive = 6000;
    private static TimeUnit unit = TimeUnit.MILLISECONDS;
    public static ThreadPoolExecutor executor ;
    public static LinkedBlockingQueue queue;




    public  void  init(){
        queue = new LinkedBlockingQueue<Runnable>(100);
        this.executor = new ThreadPoolExecutor(poolSize,maximum,keepAlive,unit,queue);
    }


    public void executor(){
        init();
        for(Integer i = 0;i < 50;i++){
            TestRunnable testR = new TestRunnable(runnableName + i);
            executor.execute(testR);
            System.out.print("Thread test : " +  ThreadImpl.executor.getPoolSize() + "\n" );
        }

    }

    private  class  TestRunnable implements  Runnable{

        private String  testName;
        private ThreadLocal<Integer> runNum = new ThreadLocal<Integer>();

        private TestRunnable(String runName){
            this.testName = runName;

        }

        @Override
        public void run() {
            Integer num = runNum.get();
            if(num == null)
                runNum.set(1);
            else
                runNum.set(++num);
            System.out.print("runNum: " +  runNum.get() );
            System.out.print("runnableName: " +  this.testName + "begin \n" );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("runnableName: " +  this.testName + "end \n" );

        }
    }

}
