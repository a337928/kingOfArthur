package com.arthur.designPatterns.commandPatterns;

/**
 * Created by wangtao on 17/4/11.
 */
public class Problem implements Task{

	private Programmer programmer;

	public Problem(Programmer programmer) {
		super();
		this.programmer = programmer;
	}

	public void handle() {
		programmer.handleProblem();
	}

	public String toString() {
		return "Problem [programmer=" + programmer.getName() + "]";
	}

}