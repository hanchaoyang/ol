package com.hanchaoyang.ol;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 操作日志注解
 *
 * @author 韩朝阳
 * @since 2023/6/16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLog {

    String value();

}