package com.arthur.designPatterns.visitorPatterns;

/**
 * Created by wangtao on 17/4/13.
 */
//比Viewer接口低一个层次的访问者接口
public abstract class AbstractViewer implements Viewer{

	//查看消费的单子
	abstract void viewConsumeBill(ConsumeBill bill);

	//查看收入的单子
	abstract void viewIncomeBill(IncomeBill bill);

	public final void viewAbstractBill(AbstractBill bill){}
}
