/**
 * Acestek.com.cn Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.neo.core.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解 拦截Controller 
 *
 * @author
 * @version
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemControllerLog {
	String description() default "";
}
