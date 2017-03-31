package com.arthur.action;

import com.arthur.bean.TestBean;
import com.arthur.mybatis.bussiness.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangtao on 17/3/31.
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisTestAction {

	@Autowired
	private MybatisTest mybatisTest;


	@RequestMapping("/insert")
	@ResponseBody
	public String insertTest(){
		TestBean testBean = new TestBean();
		testBean.setId(136);
		testBean.setName("new name");
		mybatisTest.insert(testBean);
		return "成功";
	}
}
