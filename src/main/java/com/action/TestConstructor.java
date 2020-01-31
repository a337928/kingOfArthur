package com.action;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by wangtao on 17/5/29.
 */
public class TestConstructor {
	public TestConstructor(){
		System.out.print("testConstructor");
	}

	public TestConstructor(String type){
		System.out.print("testConstructor:" + type + "\n");
	}

	public TestConstructor(String type,String name){
		System.out.print("testConstructor:" + type + ";name:" + name + "\n");
	}

	public static void main(String[] args){
		Class<?> c = TestConstructor.class;
		try {
			Class<?>[] parameterTypes = new Class[]{String.class};
			Constructor<?>[] constructor1 = c.getConstructors();
			for (Constructor<?> cs :constructor1){
				Class<?>[] pt = cs.getParameterTypes();

			}
			System.out.println("1.通过参数获取类的指定公共构造方法： ");
			Constructor<?> constructor = c.getConstructor(parameterTypes);
			System.out.println("constructor:" + constructor.toString());
			Object o = constructor.newInstance(new Object[] {
				"王滔"
			});
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
