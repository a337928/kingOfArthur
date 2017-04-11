package com.arthur.designPatterns.BridgePatterns;

/**
 * Created by wangtao on 17/4/11.
 */
public class Use {
	public static void main(String[] args) {
		Soul soul = new Doppelganger();
		soul.setAppearance(new TRH());//设置分身外貌为高富帅
		soul.setSkills(new Invisible());//设置隐身技能
		soul.doAllLikePeople();
		System.out.println("----------------------------");
		soul.show();
		soul.releaseSkills();
		System.out.println("----------------------------");
		//切换技能
		soul.setSkills(new Volant());
		soul.releaseSkills();
		System.out.println("----------------------------");
		//切换外貌
		soul.setAppearance(new Loser());
		soul.show();
		System.out.println("----------------------------");
		//切换技能
		soul.setSkills(new Volant());
		soul.releaseSkills();
	}
}
