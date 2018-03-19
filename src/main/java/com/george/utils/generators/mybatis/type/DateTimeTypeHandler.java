package com.george.utils.generators.mybatis.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.DateTime;

import java.sql.*;

/**
 * mybatis 类型扩展, 处理 {@link DateTime} 类型的数据
 *
 * @see org.apache.ibatis.type.TypeHandler
 */
public class DateTimeTypeHandler extends BaseTypeHandler<DateTime> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, DateTime parameter,
			JdbcType jdbcType) throws SQLException {
		if (jdbcType == JdbcType.DATE) {
			ps.setDate(i, new Date(parameter.getMillis()));
		}
		else if (jdbcType == JdbcType.TIME) {
			ps.setTime(i, new Time(parameter.getMillis()));
		}
		else if (jdbcType == JdbcType.TIMESTAMP) {
			ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
		}
		else {
			ps.setTimestamp(i, new Timestamp(parameter.getMillis()));
		}
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Object object = rs.getObject(columnName);
		if (object == null) {
			return null;
		}
		else if (object instanceof java.util.Date) {
			java.util.Date date = (java.util.Date) object;
			return new DateTime(date.getTime());
		}
		else if (object instanceof Long) {
			Long millis = (Long) object;
			return new DateTime(millis);
		}
		else if (object instanceof Integer) {
			int seconds = ((Integer) object).intValue();
			long millis = seconds * 1000;
			return new DateTime(millis);
		}
		else {
			return null;
		}
	}

	@Override
	public DateTime getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Object object = rs.getObject(columnIndex);
		if (object == null) {
			return null;
		}
		else if (object instanceof java.util.Date) {
			java.util.Date date = (java.util.Date) object;
			return new DateTime(date.getTime());
		}
		else {
			Long millis = (Long) object;
			return new DateTime(millis);
		}
	}

	@Override
	public DateTime getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Object object = cs.getObject(columnIndex);
		if (object == null) {
			return null;
		}
		else if (object instanceof java.util.Date) {
			java.util.Date date = (java.util.Date) object;
			return new DateTime(date.getTime());
		}
		else {
			Long millis = (Long) object;
			return new DateTime(millis);
		}
	}
}
