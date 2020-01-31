package com.test;

import java.io.IOException;

/**
 * Created by wangtao on 2017/11/14.
 */
public class CgService {
	public void  say(){
		System.out.println("CgService hello");
	}

	public void miss(){
		System.out.println("miss game");
	}

	public static void main(String[] args) throws IOException {
		CglibProxy cglibProxy = new CglibProxy();
		CgService cgService = (CgService) cglibProxy.getProxy(CgService.class);
		cgService.say();
		cgService.miss();
	}
}
