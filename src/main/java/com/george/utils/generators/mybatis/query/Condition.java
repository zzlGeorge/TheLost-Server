package com.george.utils.generators.mybatis.query;

/**
 * Created by xiam on 2015/6/4.
 */
public class Condition<T> {

	private String name;

	private LogicalOp logicalOp;

	private RelationalOp relationalOp;

	private T value;

	private Condition(LogicalOp logicalOp, String param, RelationalOp relationalOp, T value) {
		this.logicalOp = logicalOp;
		this.name = param;
		this.relationalOp = relationalOp;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public LogicalOp getLogicalOp() {
		return logicalOp;
	}

	public RelationalOp getRelationalOp() {
		return relationalOp;
	}

	public T getValue() {
		return value;
	}

	public static class Builder<T> {

		private String name;

		private LogicalOp logicalOp;

		private RelationalOp relationalOp;

		private T value;

		public Builder() {
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder logicalOp(LogicalOp logicalOp) {
			this.logicalOp = logicalOp;
			return this;
		}

		public Builder relationalOp(RelationalOp relationalOp) {
			this.relationalOp = relationalOp;
			return this;
		}

		public Builder value(T value) {
			this.value = value;
			return this;
		}

		public Condition build() {
			return new Condition(this.logicalOp, this.name, this.relationalOp, value);
		}
	}
}
