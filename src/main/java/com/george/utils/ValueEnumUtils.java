package com.george.utils;

/**
 * @author Sephy
 * @since: 2015-05-09
 */
public abstract class ValueEnumUtils {

	public static <T extends ValueEnum, E> T valToCustomEnum(E val, Class<T> enumType) {
		T[] enumConstants = enumType.getEnumConstants();
		for (T constant : enumConstants) {
			if (val.equals(constant.value())) {
				return constant;
			}
		}
		return null;
	}
}
