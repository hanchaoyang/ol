# 使用文档
1. 引入依赖
2. 创建配置类
```java
package com.example.demo.config;

import com.hanchaoyang.ol.OperationLogCollector;
import com.hanchaoyang.ol.OperationLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperationLogConfig {

    @Bean
    public OperationLogFilter operationLogFilter() {
        // 根据执行方法、参数、返回结果来判断是否需要保存该条操作日志
        return (method, args, result) -> result.getCode() == 200;
    }

    @Bean
    public OperationLogCollector operationLogCollector() {
        return content -> {
            // 保存日志
        };
    }

}
```
3. 使用注解
```java
package com.example.demo.controller;

import com.example.demo.constant.OperationLogConstant;
import com.hanchaoyang.ol.OperationLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    // #now为内置的当前时间
    // @demoService.getNameById()为DemoService内的根据ID查询名称的方法
    // #id为方法参数
    @OperationLog("#now + ' 用户' + @demoService.getNameById(#id) + '登录系统'")
    public String demo(@RequestParam Long id) {
        return "ok";
    }

}
```
最终效果
```text
2023-06-16 17:09:45 用户张三登录系统
2023-06-16 17:09:50 用户李四登录系统
```