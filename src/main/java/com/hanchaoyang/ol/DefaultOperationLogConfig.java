package com.hanchaoyang.ol;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 默认操作日志配置
 *
 * @author 韩朝阳
 * @since 2023/6/16
 */
@Configuration
public class DefaultOperationLogConfig {

    @Bean
    @ConditionalOnMissingBean
    public OperationLogFilter operationLogFilter() {
        return (method, args, result) -> true;
    }

    @Bean
    @ConditionalOnMissingBean
    public OperationLogCollector operationLogCollector() {
        return (record) -> {

        };
    }

}