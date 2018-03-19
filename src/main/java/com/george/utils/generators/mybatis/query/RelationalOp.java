package com.george.utils.generators.mybatis.query;

/**
 * Created by xiam on 2015/6/4.
 */
public enum RelationalOp implements RelationalOperator {

	/**
	 * 等于
	 */
	EQ("=", true),

	/**
	 * 不等于
	 */
	NOT_EQ("!=", true),

	/**
	 * 小于
	 */
	LT("<", true),

	/**
	 * 不小于(大于等于)
	 */
	NOT_LT(">=", true),

	/**
	 * 大于
	 */
	GT(">", true),

	/**
	 * 不大于(小于等于)
	 */
	NOT_GT("<=", true),

	/**
	 * in
	 */
	IN("IN", true),

	/**
	 * not in
	 */
	NOT_IN("NOT IN", true),

	/**
	 * 字符串相似
	 */
	LIKE("LIKE", true),

	/**
	 * 字符串相似
	 */
	NOT_LIKE("NOT LIKE", true),

	/**
	 * 为空
	 */
	IS_NULL("IS NULL", false),

	/**
	 * 不为空
	 */
	IS_NOT_NULL("IS NOT NULL", false);

	private String op;

	private boolean isBinary;

	RelationalOp(String op, boolean isBinary) {
		this.op = op;
		this.isBinary = isBinary;
	}

	@Override
	public String getOp() {
		return this.op;
	}

	@Override
	public boolean isBinary() {
		return this.isBinary;
	}
}
