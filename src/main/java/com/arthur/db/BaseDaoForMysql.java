/**
 * 
 */
package com.arthur.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;


/**
 * @date 2017-3-11 下午4:16:41
 * @author wangtao
 * @Description:
 * @project isag
 */
@Repository
public class BaseDaoForMysql extends BaseDao {
    @Resource
	protected DataSource dataSource;
	
	/*@Override
	public void afterPropertiesSet() throws Exception {
		setDataSource(dataSource);
	}*/
	
	private String getOrderSql(boolean isWhere) throws Exception{
		return "";
	}
}
