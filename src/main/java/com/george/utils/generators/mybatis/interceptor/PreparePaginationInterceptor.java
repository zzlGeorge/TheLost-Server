/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.george.utils.generators.mybatis.interceptor;

import com.george.utils.Pagine;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.DirectFieldAccessor;

import java.sql.Connection;
import java.util.Properties;

/**
 * Mybatis数据库分页插件，拦截StatementHandler的prepare方法
 *
 * @author poplar.yfyang / thinkgem
 * @version 2013-8-28
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PreparePaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = 1L;

    public PreparePaginationInterceptor() {
        super();
    }

    public Object intercept(Invocation ivk) throws Throwable {
        if (ivk.getTarget().getClass().isAssignableFrom(RoutingStatementHandler.class)) {
            final RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk
                    .getTarget();
            DirectFieldAccessor statementHandlerAccessor = new DirectFieldAccessor(statementHandler);
            final BaseStatementHandler delegate = (BaseStatementHandler) statementHandlerAccessor
                    .getPropertyValue(DELEGATE);
            DirectFieldAccessor delegateAccessor = new DirectFieldAccessor(delegate);
            final MappedStatement mappedStatement = (MappedStatement) delegateAccessor
                    .getPropertyValue(MAPPED_STATEMENT);

            // //拦截需要分页的SQL
            // // if (mappedStatement.getId().matches(_SQL_PATTERN)) {
            // if (CommonUtils.indexOfIgnoreCase(mappedStatement.getId(),
            // _SQL_PATTERN) != -1) {
            BoundSql boundSql = delegate.getBoundSql();
            // 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                log.error("参数未实例化");
                throw new NullPointerException("parameterObject尚未实例化！");
            } else {
                final Connection connection = (Connection) ivk.getArgs()[0];
                final String sql = boundSql.getSql();
                // 记录统计
                final long count = SQLHelper.getCount(connection, mappedStatement, parameterObject,
                        boundSql, log);
                Pagine<Object> page = null;
                page = convertParameter(parameterObject, page);
                page.setTotal(count);
                String pagingSql = SQLHelper.generatePageSql(sql, page, dialect);
                if (log.isDebugEnabled()) {
                    log.debug("PAGE SQL:" + pagingSql);
                }
                // 将分页sql语句反射回BoundSql.
                new DirectFieldAccessor(boundSql).setPropertyValue("sql", pagingSql);
            }

            if (boundSql.getSql() == null || "".equals(boundSql.getSql())) {
                return null;
            }

        }
        // }
        return ivk.proceed();
    }

    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    public void setProperties(Properties properties) {
        initProperties(properties);
    }
}
