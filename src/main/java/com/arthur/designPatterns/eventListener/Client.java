package com.arthur.designPatterns.eventListener;

/**
 * Created by wangtao on 17/4/8.
 */
public class Client {
	public static void main(String[] args) {
		ButtonJsp jsp = new ButtonJsp();//客户访问了我们的这个JSP页面
		//以下客户开始在按钮上操作
		jsp.getButton().dblClick();//双击按钮
		jsp.getButton().mouseMove(10, 100);//移动到10，100
		jsp.getButton().mouseMove(15, 90);//又移动到15,90
		jsp.getButton().click();//接着客户点了提交
	}
}
