/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.george.utils.generators.mybatis.interceptor;

import com.george.utils.Pagine;
import com.george.utils.generators.mybatis.dialect.*;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.DirectFieldAccessor;

import java.io.Serializable;
import java.util.Properties;

/**
 * Mybatis分页拦截器基类
 * @author poplar.yfyang / thinkgem
 * @version 2013-8-28
 */
public abstract class BaseInterceptor implements Interceptor, Serializable {

	private static final long serialVersionUID = 1L;

	protected static final String DELEGATE = "delegate";

	protected static final String MAPPED_STATEMENT = "mappedStatement";

	protected Log log = LogFactory.getLog(this.getClass());

	protected Dialect dialect;

	/**
	 * 对参数进行转换和检查
	 * @param parameterObject 参数对象
	 * @param page 分页对象
	 * @return 分页对象
	 * @throws NoSuchFieldException 无法找到参数
	 */
	@SuppressWarnings("unchecked")
	protected static <E> Pagine<E> convertParameter(Object parameterObject, Pagine<E> page) {
		try {
			if (parameterObject instanceof Pagine) {
				return (Pagine) parameterObject;
			}
			else {
				Object value = new DirectFieldAccessor(parameterObject)
						.getPropertyValue(PagineConsts.PAGE);
				return (Pagine) value;
			}
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * 设置属性，支持自定义方言类和制定数据库的方式 <code>dialectClass</code>,自定义方言类。可以不配置这项
	 * <ode>dbms</ode> 数据库类型，插件支持的数据库 <code>sqlPattern</code> 需要拦截的SQL ID
	 * @param p 属性
	 */
	protected void initProperties(Properties p) {
		String dialect = p.getProperty("dialect");
		if (dialect == null) {
			throw new RuntimeException("mybatis dialect error.");
		}
		dialect = dialect.toLowerCase();
		if ("db2".equals(dialect)) {
			this.dialect = new DB2Dialect();
		}
		else if ("derby".equals(dialect)) {
			this.dialect = new DerbyDialect();
		}
		else if ("h2".equals(dialect)) {
			this.dialect = new H2Dialect();
		}
		else if ("hsql".equals(dialect)) {
			this.dialect = new HSQLDialect();
		}
		else if ("mysql".equals(dialect)) {
			this.dialect = new MySQLDialect();
		}
		else if ("oracle".equals(dialect)) {
			this.dialect = new OracleDialect();
		}
		else if ("postgre".equals(dialect)) {
			this.dialect = new PostgreSQLDialect();
		}
		else if ("mssql".equals(dialect) || "sqlserver".equals(dialect)) {
			this.dialect = new SQLServer2005Dialect();
		}
		else if ("sybase".equals(dialect)) {
			this.dialect = new SybaseDialect();
		}
		if (this.dialect == null) {
			throw new RuntimeException("mybatis dialect error.");
		}
	}
}
