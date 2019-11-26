package com.jhg.marketing.common;

import java.lang.annotation.*;
import java.util.List;

/**
 *目前用于对某个字段进行描述
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.FIELD})
public @interface Explain {

	//展示的值
	String value();

	//展示的类型（text,select...）
	String dataType() default "";

	//表单是否显示
	boolean isEdit() default true;

	//表格是否显示
	boolean isShow() default true;

	//
	boolean autoValue() default false;
	int order() default 0;

	//表单是否必填
	boolean required() default false;

	String dataTokens() default "";

}

