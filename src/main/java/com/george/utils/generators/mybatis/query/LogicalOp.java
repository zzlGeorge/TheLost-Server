package com.george.utils.generators.mybatis.query;

/**
 * Created by xiam on 2015/6/4.
 */
public enum LogicalOp implements Operator {

	AND("AND");

    // OR("OR");

	private String op;

	LogicalOp(String op) {
		this.op = op;
	}

	@Override
	public String getOp() {
		return this.op;
	}
}
