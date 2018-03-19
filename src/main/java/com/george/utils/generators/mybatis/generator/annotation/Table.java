package com.george.utils.generators.mybatis.generator.annotation;

import java.lang.annotation.*;

/**
 * Created by xiam on 2015/6/23.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Table {

    String name() default "";
}
