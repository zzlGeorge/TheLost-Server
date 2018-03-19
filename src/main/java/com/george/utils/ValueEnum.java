package com.george.utils;

/**
 * @author Sephy
 * @since: 2015-05-09
 */
public interface ValueEnum<T extends Enum<T>, E> {
	E value();
}
