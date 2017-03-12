package com.action;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangtao on 17/3/11.
 */

public class TestAction {
    @RequestMapping("jack")
    String home(Model model) {
        return "Hello World!";
    }
}
