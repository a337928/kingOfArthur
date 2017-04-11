package com.arthur.designPatterns.BridgePatterns;

/**
 * Created by wangtao on 17/4/11.
 */
//分身类
public class Doppelganger extends Soul{

	public Doppelganger(){
		System.out.println("制作一个暂无外貌和技能的分身");
	}

	public void doAllLikePeople(){
		System.out.println("分身可以像正常人一样做任何事");
	}

}
