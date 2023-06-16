package com.hanchaoyang.ol;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 类介绍
 *
 * @author 韩朝阳
 * @since 2023/6/16
 */
public interface OperationLogFilter {

    boolean isCollectable(Method method, Map<String, Object> args, Object result);

}