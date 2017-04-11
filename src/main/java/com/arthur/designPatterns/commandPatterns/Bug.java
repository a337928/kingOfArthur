package com.arthur.designPatterns.commandPatterns;

/**
 * Created by wangtao on 17/4/11.
 */
public class Bug implements Task{

	private Programmer programmer;

	public Bug(Programmer programmer) {
		super();
		this.programmer = programmer;
	}

	public void handle() {
		programmer.handleBug();
	}

	public String toString() {
		return "Bug [programmer=" + programmer.getName() + "]";
	}

}
