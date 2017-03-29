package com.arthur.business.impl;

import com.arthur.bean.TestBean;
import com.arthur.business.SpringTransactionExercise;
import com.arthur.db.BaseDaoForMysql;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by wangtao on 17/3/28.
 */
@Service
public class SpringTransactionExerciseImpl implements SpringTransactionExercise {

    @Resource
    private BaseDaoForMysql baseDaoForMysql;

    @Resource
    private DataSource dataSource;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Boolean insert(TestBean testbean) {
        baseDaoForMysql.setDataSource(dataSource);
        try {
            testbean.setId(10000);
            baseDaoForMysql.update("insert into test(id,name) values (?,?) ",new Object[]{
                    testbean.getId(),
                    testbean.getName()
            });

            testbean.setId(10080);
            baseDaoForMysql.update("insert into test(id,name) values (?,?) ",new Object[]{
                    testbean.getId(),
                    testbean.getName()
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
