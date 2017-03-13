package com.arthur.annotationProcess;

import com.arthur.annotation.Query;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by wangtao on 17/3/13.
 */
public class ProcessQuery {

    public static void taskQuery(Class<?> cl){
        int i = 0;
        for(Method method:cl.getDeclaredMethods()){
            Query q = method.getAnnotation(Query.class);
            //for(Annotation annotation : annotations){
            //    Query q = (Query) annotation;
                if (q != null)
                    System.out.print("sql:"+ q.sql() +" NO."+ i++ );
            //}


        }

    }
}
