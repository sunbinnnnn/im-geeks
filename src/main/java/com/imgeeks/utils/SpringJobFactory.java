/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: SpringBeanJobFactory.java
 * Author:   Tommy Xu
 * Date:     Dec 21, 2014 11:46:18 AM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.imgeeks.utils;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SpringJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.scheduling.quartz.SpringBeanJobFactory#createJobInstance(org.quartz.spi.TriggerFiredBundle)
     */
    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        Object jobInstance = super.createJobInstance(bundle);
        applicationContext.getAutowireCapableBeanFactory().autowireBean(jobInstance);
        return jobInstance;
    }
}