package com.hanchaoyang.ol;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志切面
 *
 * @author 韩朝阳
 * @since 2023/6/16
 */
@Aspect
@Component
public class OperationLogAspect {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final OperationLogFilter filter;

    private final OperationLogCollector collector;

    private final SpringUtil springUtil;

    public OperationLogAspect(OperationLogFilter filter, OperationLogCollector collector, SpringUtil springUtil) {
        this.filter = filter;
        this.collector = collector;
        this.springUtil = springUtil;
    }

    @Around("@annotation(operationLog)")
    public Object doAround(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        Map<String, Object> args = this.getArgs(joinPoint);

        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long end = System.currentTimeMillis();

        if (!this.filter.isCollectable(method, args, result)) {
            return result;
        }

        ExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

        this.setCommonVariable(evaluationContext);
        this.setVariable(evaluationContext, args);
        this.setBeanResolver(evaluationContext);

        String content = parser.parseExpression(operationLog.value()).getValue(evaluationContext, String.class);

        OperationLogRecord record = new OperationLogRecord(LocalDateTime.now(), method, args, result, content, end - start);

        this.collector.collect(record);

        return result;
    }

    private void setCommonVariable(StandardEvaluationContext evaluationContext) {
        evaluationContext.setVariable("now", LocalDateTime.now().format(this.dateTimeFormatter));
    }

    private void setVariable(StandardEvaluationContext evaluationContext, Map<String, Object> args) {
        args.forEach(evaluationContext::setVariable);
    }

    private void setBeanResolver(StandardEvaluationContext evaluationContext) {
        evaluationContext.setBeanResolver((nestedEvaluationContext, beanName) -> this.springUtil.getBean(beanName, Object.class));
    }

    private Map<String, Object> getArgs(ProceedingJoinPoint joinPoint) {
        Map<String, Object> args = new HashMap<>();

        String[] argNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Object[] argValues = joinPoint.getArgs();

        for (int i = 0; i < argNames.length; i++) {
            args.put(argNames[i], argValues[i]);
        }

        return args;
    }

}