package com.wdk.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: pengmin
 * @date: 2021/12/16 13:55
 */
@Configuration
@EnableAsync
public class ThreadPool {

    public static final String POOL_TASK_EXECUTOR = "invoiceConsumerPool";

    @Bean(name = POOL_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //  核心线程数;
        threadPoolTaskExecutor.setCorePoolSize(5);
        // 最大线程数;
        threadPoolTaskExecutor.setMaxPoolSize(20);
        // 缓冲队列;
        threadPoolTaskExecutor.setQueueCapacity(30);
        // 允许的最大空闲时间;
        threadPoolTaskExecutor.setKeepAliveSeconds(200);
        threadPoolTaskExecutor.setThreadNamePrefix(POOL_TASK_EXECUTOR + "-");
        // 拒绝策略设置重试添加;
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
