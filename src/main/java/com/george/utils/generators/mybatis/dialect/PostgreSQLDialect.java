package com.george.utils.generators.mybatis.dialect;

import com.george.utils.generators.mybatis.interceptor.PagineConsts;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yu on 2017/5/31.
 */
public class PostgreSQLDialect extends AbstractDialect {

    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, Integer.toString(offset), Integer.toString(limit));
    }

    /**
     * 将sql变成分页sql语句,提供将offset及limit使用占位符号(placeholder)替换.
     *
     * <pre>
     * 如mysql
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 将返回
     * select * from user limit :offset,:limit
     * </pre>
     *
     * @param sql 实际SQL语句
     * @param offset 分页开始纪录条数
     * @param offsetPlaceholder 分页开始纪录条数－占位符号
     * @param limitPlaceholder 分页纪录条数占位符号
     * @return 包含占位符的分页sql
     */
    public String getLimitString(String sql, int offset, String offsetPlaceholder,
                                 String limitPlaceholder) {
        StringBuilder pageSql = new StringBuilder().append(sql);
        pageSql = offset <= 0 ? pageSql.append(" limit ").append(limitPlaceholder) : pageSql
                .append(" limit ").append(limitPlaceholder).append(" offset ")
                .append(offsetPlaceholder);
        return pageSql.toString();
    }

    @Override
    public String getPagineSql(String origSql) {
        return new StringBuilder(origSql).append(" limit ? offset ?").toString();
    }

    @Override
    public BoundSql getPagineBoundSql(Configuration configuration, BoundSql boundSql) {
        String pageSql = getPagineSql(boundSql.getSql());
        List<ParameterMapping> parameterMappings = new ArrayList<ParameterMapping>(boundSql.getParameterMappings());
        parameterMappings.add(new ParameterMapping.Builder(configuration, PagineConsts.PAGE_OFFSET,
                Integer.class).build());
        parameterMappings.add(new ParameterMapping.Builder(configuration, PagineConsts.PAGE_SIZE,
                Integer.class).build());
        return new BoundSql(configuration, pageSql, parameterMappings,
                boundSql.getParameterObject());
    }
}
