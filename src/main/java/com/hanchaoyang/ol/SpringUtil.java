package com.hanchaoyang.ol;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Spring工具
 *
 * @author 韩朝阳
 * @since 2023/6/16
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public <T> T getBean(String beanName, Class<T> beanType) {
        return this.applicationContext.getBean(beanName, beanType);
    }

}