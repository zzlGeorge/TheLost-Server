package com.george.utils.generators.mybatis.query;



import org.apache.commons.lang.StringUtils;

import java.lang.annotation.*;

/**
 * Created by xiam on 2015/6/4.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
@Documented
public @interface CriteriaCondition {

	/**
	 * 要体现到数据库中的字段名称, 默认会取字段名
	 *
	 * @return
	 */
	String name() default StringUtils.EMPTY;

	/**
	 * 条件关系, 默认为 and
	 *
	 * @return
	 */
	LogicalOp logicalOp() default LogicalOp.AND;

	/**
	 * 运算符, 默认为 =
	 *
	 * @return
	 */
	RelationalOp relationalOp() default RelationalOp.EQ;
}
