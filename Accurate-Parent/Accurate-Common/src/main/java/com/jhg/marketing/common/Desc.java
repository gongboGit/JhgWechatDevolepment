package com.jhg.marketing.common;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Repeatable(DescContainer.class)
public @interface Desc {
	
	String level();
	String title();
	String role() default "";
	String plevel() default "";
	String icon() default "";
	boolean isShowMenu() default true;
	String mark() default "";
	
}
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@interface DescContainer{
	Desc[]value();
}

