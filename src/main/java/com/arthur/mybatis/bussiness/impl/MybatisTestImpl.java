package com.arthur.mybatis.bussiness.impl;

import com.arthur.bean.TestBean;
import com.arthur.mybatis.bussiness.MybatisTest;
import com.arthur.mybatis.mybatisDao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangtao on 17/3/30.
 */
@Service
public class MybatisTestImpl implements MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public void insert(TestBean testBean) {
        for(int i = 0 ; i < 3;i ++)
            userMapper.insert(testBean.getId(),testBean.getName());
    }
}
