package com.george.utils.generators.mybatis.dialect;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.Configuration;

/**
 * Created by yu on 2017/5/31.
 */
public abstract class AbstractDialect implements Dialect {

//    @Override
    public boolean supportsLimit() {
        return false;
    }

//    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return null;
    }

//    @Override
    public String getPagineSql(String origSql) {
        return null;
    }

//    @Override
    public BoundSql getPagineBoundSql(Configuration configuration, BoundSql boundSql) {
        return null;
    }
}
