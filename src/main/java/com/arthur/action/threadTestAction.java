package com.arthur.action;

import com.arthur.threadLearn.IThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangtao on 17/3/18.
 */
@RestController
@RequestMapping("/thread")
public class threadTestAction {

    @Autowired
    public IThread threadTest;

    @RequestMapping("/learn")
    @ResponseBody
    public String threadAction() {

        threadTest.executor();

        return  null;
    }




}
