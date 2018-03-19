package com.george.utils.generators.mybatis.annotation;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * 自定义的 mybatis 映射接口扫描注解 配合
 * {@link org.mybatis.spring.mapper.MapperScannerConfigurer} 使用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Repository
public @interface MyBatisMapper {
	String value() default "";
}