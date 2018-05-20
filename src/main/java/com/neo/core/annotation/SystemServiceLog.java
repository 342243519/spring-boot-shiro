/**
 * Acestek.com.cn Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.neo.core.annotation;

import java.lang.annotation.*;

/**
 *自定义注解 拦截service
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemServiceLog {
	String description() default "";
}
