package com.arthur.designPatterns.visitorPatterns;

/**
 * Created by wangtao on 17/4/13.
 */
public class IncomeBill extends AbstractBill {
	public IncomeBill(double amount, String item) {
		super(amount, item);
	}

	public void accept(Viewer viewer) {
		if (viewer instanceof AbstractViewer) {
			((AbstractViewer)viewer).viewIncomeBill(this);
			return;
		}
		viewer.viewAbstractBill(this);
	}
}
