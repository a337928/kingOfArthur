package com.arthur.designPatterns.commandPatterns;

/**
 * Created by wangtao on 17/4/11.
 */
public class Work {

	public static void main(String[] args) {
		Programmer xiaozuo = new Programmer("小左");
		Programmer xiaoyou = new Programmer("小右");
		ProductManager productManager = new ProductManager(xiaozuo,xiaoyou);

		Salesman salesmanA = new Salesman("A",productManager);
		Salesman salesmanB = new Salesman("B",productManager);
		Salesman salesmanC = new Salesman("C",productManager);
		Salesman salesmanD = new Salesman("D",productManager);

		salesmanA.putDemand();
		salesmanB.putDemand();
		salesmanB.putBug();
		salesmanC.putDemand();
		salesmanC.putProblem();
		salesmanD.putDemand();

		System.out.println("第一天产品经理分配任务");
		productManager.assign();
		productManager.printTaskList();
		System.out.println("第二天产品经理分配任务");
		productManager.assign();
		productManager.printTaskList();
	}

}
