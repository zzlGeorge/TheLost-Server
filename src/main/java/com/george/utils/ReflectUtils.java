/*
 * Copyright 2015 Ming Xia
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.george.utils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 反射工具类
 *
 * @author Sephy
 * @since: 2015-06-04
 */
public abstract class ReflectUtils {

	/**
	 * <p>
	 * 获取指定 {@link Class} 类型声明的所有 {@link Field} 字段,
	 * </p>
	 * <p>
	 * 包括继承的父类的字段
	 * </p>
	 *
	 * @param klass
	 * @return
	 */
    public static Collection<Field> getAllFields(Class<?> klass) {
        Set<Field> fields = new HashSet<Field>();
        while (klass != null) {
            Field[] declaredFields = klass.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                if (!declaredField.getName().equals("serialVersionUID")
                        && !fields.contains(declaredField)) {
                    fields.add(declaredField);
                }
            }
            // 获取父类中声明的字段
            klass = klass.getSuperclass();
        }
        return fields;
    }
}
