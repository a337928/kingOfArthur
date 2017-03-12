package com.arthur.db;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import com.arthur.exception.MngException;
import com.arthur.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.hibernate.Session;


public abstract class BaseDao {
	protected static final Logger log = Logger.getLogger(BaseDao.class);

	protected DataSource dataSource = null;

	protected Session session = null;

	protected static final String ERROR_CODE = "error.sql.error";

	protected static final String ERROR_CODE_QUERY = "error.sqlQuery.error";

	protected List alArea = new ArrayList();

	protected List alOrder = new ArrayList(); // 格式：a.field desc

	protected static final String DB_TYPE_ORALCE = "oracle";

	protected static final String DB_TYPE_MYSQL = "mysql";

	protected static final String DB_TYPE_SQLSERVER = "sqlserver";

	protected static String db_type = "";

	private static String SQL_DEBUG = "";

	private static long MONITOR_SQL_TIME = 0;

	private static long MONITOR_LIST_SIZE = 0;

	static {
		if (db_type == null || "".equals(db_type)) {
			try {
				db_type = "";
			} catch (Exception e) {
				log.info("未设置数据库类型或者格式不正确，系统默认为oracle！");
				db_type = DB_TYPE_MYSQL;
			}
		}
		if (db_type == null || "".equals(db_type)) {
			db_type = DB_TYPE_MYSQL;
		}



	}

	public List getAlArea() {
		return alArea;
	}

	public void setAlArea(List alArea) {
		this.alArea = alArea;
	}

	public List getAlOrder() {
		return alOrder;
	}

	public void setAlOrder(List alOrder) {
		this.alOrder = alOrder;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	 * 限制：在标准SQL里不能出现的字段或表别名：表别名pageView;字段pageNum @function 得到不同数据库的分页技术 @param sql
	 * :标准的SQL语句 @param pageStart 页开始 @param pageSize 页大小 @param db_type 数据库类型 @return sql
	 * :带分页的SQL语句
	 */
	public static String getPageSQL(String sql, long pageStart, int pageSize) {
		long pageEnd = pageStart + pageSize;
		StringBuffer retStr = new StringBuffer(sql);
		if (db_type.equals("oracle")) {
			sql = sql.trim();
			StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
			pagingSelect.append(sql);
			pagingSelect.append(" ) row_ where rownum <= ");
			pagingSelect.append(pageEnd);
			pagingSelect.append(") where rownum_ > ");
			pagingSelect.append(pageStart);
			return pagingSelect.toString();

		} else if (db_type.equals("sqlserver")) {

		} else if (db_type.equalsIgnoreCase("mysql")) {
			sql = sql + " limit " + pageStart + ", " + pageSize;
			return sql;
		}
		return retStr.toString();
	}

	protected static Long getLongValue(Object obj) {
		if (obj == null) {
			return new Long(0);
		}
		return new Long(obj.toString());
	}

	protected static Double getDoubleValue(Object obj) {
		if (obj == null) {
			return new Double(0);
		}
		return new Double(obj.toString());
	}

	protected static Integer getIntegerValue(Object obj) {
		if (obj == null) {
			return new Integer(0);
		}
		return new Integer(obj.toString());
	}

	protected static String getStringValue(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	protected static String getDateValue(Object obj) {
		return DateUtil.formatDate((Date) obj, 0);
	}

	protected static String getDateTimeValue(Object obj) {
		return DateUtil.formatDate((Date) obj, 4);
	}

	public static String getBeginDateSql(String field, String beginDate) {
		return " and TO_CHAR(" + field + ",'yyyy-MM-dd') >= '" + beginDate + "' ";
	}

	public static String getEndDateSql(String field, String endDate) {
		return " and TO_CHAR(" + field + ",'yyyy-MM-dd') <= '" + endDate + "' ";
	}

	private static String getSql(String sql, Object[] obj) {
		if (obj == null) {
			return sql;
		}
		String sqlTemp = "";
		if ("true".equals(SQL_DEBUG)) {
			String sqls[] = sql.split("[?]");
			for (int i = 0; i < sqls.length; i++) {
				sqlTemp += sqls[i];
				if (i < sqls.length - 1) {
					sqlTemp += "'" + dealNull(obj[i]) + "'";
				}
			}

			if (sql.endsWith("?")) {
				sqlTemp += "'" + dealNull(obj[sqls.length-1]) + "'";
			}
		}
		return sqlTemp;
	}

	protected static String printSql(String sql, Object[] obj) {
		return getSql(sql, obj);
	}


	private static String dealNull(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}



	public int update(String sql, Object[] objects) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int result = jdbcTemplate.update(sql, objects);
			return result;
		} catch (DataAccessException e) {
			log.error(getSql(sql, objects));
			throw new MngException(ERROR_CODE, e);
		}
	}

	public int update(String sql, Object[] objects, int[] ints) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int result = jdbcTemplate.update(sql, objects, ints);
			return result;
		} catch (DataAccessException e) {
			log.error(getSql(sql, objects));
			throw new MngException(ERROR_CODE, e);
		}
	}

	public int[] batchUpdate(String sql,
		BatchPreparedStatementSetter batchPreparedStatementSetter) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			int[] result = jdbcTemplate.batchUpdate(sql, batchPreparedStatementSetter);
			return result;
		} catch (DataAccessException e) {
			log.error(getSql(sql, new Object[] {}));
			throw new MngException(ERROR_CODE, e);
		}
	}

	public long queryForLong(String sql) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			long result = jdbcTemplate.queryForObject(sql,Long.class);
			return result;
		} catch (DataAccessException e) {
			log.error(sql);
			throw new MngException(ERROR_CODE, e);
		}
	}

	public long queryForLong(String sql, Object[] objects) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			long result = jdbcTemplate.queryForObject(sql,objects,Long.class);
			return result;
		} catch (DataAccessException e) {
			log.error(getSql(sql, objects));
			throw new MngException(ERROR_CODE, e);
		}
	}

	public Map queryForMap(String sql, Object[] objects) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			Map result = jdbcTemplate.queryForMap(sql, objects);
			return result;
		} catch (IncorrectResultSizeDataAccessException e) {
			log.error(getSql(sql, objects));
			throw new MngException(ERROR_CODE_QUERY, e);
		} catch (DataAccessException e) {
			log.error(getSql(sql, objects));
			throw new MngException(ERROR_CODE, e);
		}
	}

	public Map queryForMap(String sql) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			Map result = jdbcTemplate.queryForMap(sql);
			return result;
		} catch (IncorrectResultSizeDataAccessException e) {
			log.error(sql);
			throw new MngException(ERROR_CODE_QUERY, e);
		} catch (DataAccessException e) {
			log.error(sql);
			throw new MngException(ERROR_CODE, e);
		}
	}

	public List queryForList(String sql) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List result = jdbcTemplate.queryForList(sql);
			return result;
		} catch (DataAccessException e) {
			log.error(sql);
			throw new MngException(ERROR_CODE, e);
		}
	}

	public List queryForList(String sql, Object[] objects) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			List result = jdbcTemplate.queryForList(sql, objects);
			return result;
		} catch (DataAccessException e) {
			log.error(getSql(sql, objects));
			throw new MngException(ERROR_CODE, e);
		}
	}

	public Map call(CallableStatementCreator callableStatementCreator, List paramList)
		throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			Map result = jdbcTemplate.call(callableStatementCreator, paramList);
			return result;
		} catch (DataAccessException e) {
			throw new MngException(ERROR_CODE, e);
		}
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
