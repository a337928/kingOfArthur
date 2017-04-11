package com.arthur.designPatterns.BridgePatterns;

/**
 * Created by wangtao on 17/4/11.
 */
//抽象灵魂类
public abstract class Soul {

	protected Appearance appearance;//外貌

	protected Skills skills;//技能

	//具体的实现交给实现部分处理
	public void show(){
		appearance.show();
	}
	//具体的实现交给实现部分处理
	public void releaseSkills(){
		skills.releaseSkills();
	}

	public void createDoppelganger(){
		System.out.println("制作分身");
	}
	//抽象的方法，留给分身实现
	public abstract void doAllLikePeople();

	public Soul() {
		super();
	}

	public Soul(Skills skills) {
		super();
		this.skills = skills;
	}

	public Soul(Appearance appearance) {
		super();
		this.appearance = appearance;
	}

	public Soul(Appearance appearance, Skills skills) {
		super();
		this.appearance = appearance;
		this.skills = skills;
	}

	public Appearance getAppearance() {
		return appearance;
	}

	public void setAppearance(Appearance appearance) {
		this.appearance = appearance;
	}

	public Skills getSkills() {
		return skills;
	}

	public void setSkills(Skills skills) {
		this.skills = skills;
	}

}
