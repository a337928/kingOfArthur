package com.arthur.designPatterns.visitorPatterns;

/**
 * Created by wangtao on 17/4/13.
 */
//消费的单子
public class ConsumeBill extends AbstractBill{

	public ConsumeBill(double amount, String item) {
		super(amount, item);
	}

	public void accept(Viewer viewer) {
		if (viewer instanceof AbstractViewer) {
			((AbstractViewer)viewer).viewConsumeBill(this);
			return;
		}
		viewer.viewAbstractBill(this);
	}

}