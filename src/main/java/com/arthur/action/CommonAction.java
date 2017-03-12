package com.arthur.action;

import com.arthur.bean.TestBean;
import com.arthur.db.BaseDaoForMysql;
import com.arthur.util.BeanAssembly;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class CommonAction {

    @Resource
    private SessionFactory sessionFactory;

    @Resource
    private BaseDaoForMysql baseDaoForMysql;

    @Resource
    private DataSource dataSource;

    @RequestMapping(value = "/demo.do", method = RequestMethod.GET)
    String home() {
        return "Hello World!";
    }

    @RequestMapping(value ="/aa")
    public void test(HttpServletResponse response)  {
        List list = null;
        try {
            baseDaoForMysql.setDataSource(dataSource);
            list = baseDaoForMysql.queryForList("select * from test ");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("调用到了aa页面!");
        try {
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-cache");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"msg\":\"调用成功\"} \n");
            for (Object map:list){
                Map row = (Map)map;
                TestBean testB = (TestBean) BeanAssembly.getBean(row, TestBean.class);
                response.getWriter().write("bean 反射 \n");
                response.getWriter().write("id:"+ testB.getId() + " name:" + testB.getName() + "\n");


            }
            response.getWriter().write("list 反射 \n");
            List<TestBean> tbs = BeanAssembly.getBeans(list,TestBean.class);
            for (TestBean tb : tbs)
                response.getWriter().write("id:"+ tb.getId() + " name:" + tb.getName() + "\n");



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}