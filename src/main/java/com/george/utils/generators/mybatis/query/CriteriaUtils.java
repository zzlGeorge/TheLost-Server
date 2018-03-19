package com.george.utils.generators.mybatis.query;

import com.george.utils.ReflectUtils;
import com.george.utils.generators.mybatis.search.Criteria;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xiam on 2015/6/4.
 */
public abstract class CriteriaUtils {

	/**
	 * 根据 {@link com.george.utils.generators.mybatis.query} 对象生成 where 条件集合
	 * @param criteria
	 * @return
	 */
	public static List<Condition<Object>> getConditions(Criteria criteria) {
		Class<? extends Criteria> klass = criteria.getClass();
		Collection<Field> fields = ReflectUtils.getAllFields(klass);
		BeanWrapper beanWrapper = new BeanWrapperImpl(criteria);
		List<Condition<Object>> conditions = new ArrayList<Condition<Object>>();
		for (Field field : fields) {
			Object value = beanWrapper.getPropertyValue(field.getName());
			if (value != null) {
				Condition condition = null;
				ConditionIgnore ignore = field.getAnnotation(ConditionIgnore.class);
				if (ignore != null) {
					continue;
				}
				CriteriaCondition anno = field.getAnnotation(CriteriaCondition.class);
				if (anno == null) {
					condition = new Condition.Builder().name(field.getName())
							.logicalOp(LogicalOp.AND).relationalOp(RelationalOp.EQ).value(value)
							.build();
				}
				else {
					String name = anno.name();
					if (StringUtils.EMPTY.equals(name)) {
						name = field.getName();
					}
					LogicalOp logicalOp = anno.logicalOp();
					RelationalOp relationalOp = anno.relationalOp();
					if (relationalOp == RelationalOp.LIKE) { // 字符串模糊查询
						value = "%" + value + "%";
					}
					condition = new Condition.Builder().name(name).logicalOp(logicalOp)
							.relationalOp(relationalOp).value(value).build();
				}
				conditions.add(condition);
			}
		}
		return conditions;
	}
}
