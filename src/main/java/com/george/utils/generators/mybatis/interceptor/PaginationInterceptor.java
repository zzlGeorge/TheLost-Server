/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.george.utils.generators.mybatis.interceptor;

import com.george.utils.Pagine;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.DirectFieldAccessor;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 数据库分页插件，只拦截查询语句.
 *
 * @author poplar.yfyang / thinkgem
 * @version 2013-8-28
 */
@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class,
        Object.class, RowBounds.class, ResultHandler.class})})
public class PaginationInterceptor extends BaseInterceptor {

    private static final long serialVersionUID = -92945790765617549L;

    private static final Logger LOG = LoggerFactory.getLogger(PaginationInterceptor.class);

    public Object intercept(Invocation invocation) throws Throwable {
        final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        Object parameter = invocation.getArgs()[1];

        // 获取分页参数对象
        Pagine<Object> pagine = null;
        if (parameter instanceof Pagine) {
            pagine = (Pagine) parameter;
        } else if (parameter instanceof MapperMethod.ParamMap) {
            MapperMethod.ParamMap<Object> paramMap = (MapperMethod.ParamMap<Object>) parameter;
            if (paramMap.containsKey(PagineConsts.PAGE)) {
                pagine = (Pagine<Object>) paramMap.get(PagineConsts.PAGE);
            }
        }

        if (pagine == null) { // 没有设置分页对象，直接查询
            return invocation.proceed();
        }

        Executor executor = (Executor) invocation.getTarget();
        Transaction transaction = executor.getTransaction();
        // 如果设置了分页对象，就进行分页
        List<Object> queryResult = null;
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();
        if (StringUtils.isBlank(boundSql.getSql())) {
            return null;
        }
        // 得到总记录数
        long count = SQLHelper.getCount(transaction.getConnection(), mappedStatement,
                parameterObject, boundSql, log);

        pagine.setTotal(count);
        if (count > 0) { // 总记录数大于0才进行分页查询
            // 分页查询 本地化对象 修改数据库注意修改实现
            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
            Configuration configuration = mappedStatement.getConfiguration();
            BoundSql newBoundSql = SQLHelper.generatePagineBoundSql(configuration, boundSql,
                    dialect);
            // 解决MyBatis 分页foreach 参数失效 start
            DirectFieldAccessor cpfrom = new DirectFieldAccessor(boundSql);
            DirectFieldAccessor cpTo = new DirectFieldAccessor(newBoundSql);
            cpTo.setPropertyValue("metaParameters", cpfrom.getPropertyValue("metaParameters"));
            // 解决MyBatis 分页foreach 参数失效 end
            MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(
                    newBoundSql));

            invocation.getArgs()[0] = newMs;
            queryResult = (List<Object>) invocation.proceed();
        } else {
            queryResult = Collections.emptyList();
        }
        pagine.setContent(queryResult);
        return queryResult;
    }

    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    public void setProperties(Properties properties) {
        super.initProperties(properties);
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        return builder.build();
    }

    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }
}
