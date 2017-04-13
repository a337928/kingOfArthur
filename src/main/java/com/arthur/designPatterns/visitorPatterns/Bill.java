package com.arthur.designPatterns.visitorPatterns;

/**
 * Created by wangtao on 17/4/13.
 */
//单个单子的接口（相当于Element）
public interface Bill {

	void accept(Viewer viewer);

}
