package com.george.utils.generators.mybatis.type;

import com.george.utils.ValueEnum;
import com.george.utils.ValueEnumUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Sephy
 * @since: 2015-05-09
 */
public class CustomEnumTypeHandler<E extends ValueEnum> extends BaseTypeHandler<E> {

	private Class<E> type;

	public CustomEnumTypeHandler(Class<E> type) {
		if (type == null)
			throw new IllegalArgumentException("Type argument cannot be null");
		this.type = type;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setObject(i, parameter.value());
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		Object v = rs.getObject(columnName);
		return v == null ? null : ValueEnumUtils.valToCustomEnum(v, type);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Integer v = rs.getInt(columnIndex);
		return v == null ? null : ValueEnumUtils.valToCustomEnum(v, type);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		Integer v = cs.getInt(columnIndex);
		return v == null ? null : ValueEnumUtils.valToCustomEnum(v, type);
	}
}
