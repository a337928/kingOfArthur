package com.arthur.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangtao on 17/3/11.
 */
public class BeanAssembly {

    public static Object getBean(Map map,Class bean)
    {

        Class clazz = null;
        Object returnBean = null;
        try {
            clazz = Class.forName(bean.getName());
            returnBean = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods){
            if(method.getName().startsWith("set")){
                try {

                    String valueName = method.getName().substring(3);
                    method.invoke(returnBean,map.get(valueName));

                }  catch (IllegalAccessException e) {
                    e.printStackTrace();
                }  catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }

        return returnBean;
    }

    public static <T> List<T> getBeans(List list, Class bean){
        Class clazz = null;
        try {
            clazz = Class.forName(bean.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<T> returnList = new ArrayList<T>();
        for(Object obj:list){
            Map row = (Map)obj;
            T b = (T)getBean(row, bean);
            returnList.add(b);
        }
        return returnList;
    }
}
