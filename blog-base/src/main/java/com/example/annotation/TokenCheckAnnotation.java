package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 修饰范围
@Retention(RetentionPolicy.RUNTIME) // 用来描述注解的声明周期
public @interface TokenCheckAnnotation {
}
