package com.wdk.redis.consumer;

import com.wdk.redis.config.ThreadPool;
import com.wdk.redis.entity.Message;
import com.wdk.redis.entity.MessageEnum;
import com.wdk.redis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: pengmin
 * @date: 2021/12/16 11:40
 */
@Component
public class InvoiceMessageConsumer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MessageService messageService;

    @Autowired
    @Qualifier(value = ThreadPool.POOL_TASK_EXECUTOR)
    private ThreadPoolTaskExecutor executor;

    @PostConstruct
    public void initConsumer() {
        System.out.println(">>>>>>>初始化消费者,监听redis消息队列.");
        executor.execute(() -> consumeInvoiceMessage());
    }

    /**
     * 消费开票消息;
     */
    public void consumeInvoiceMessage() {
        while (true) {
            String messageInfo = null;
            Integer messageId = null;
            try {
                messageInfo = redisTemplate.opsForList().rightPop(MessageEnum.INVOICE.getName(), 0, TimeUnit.SECONDS);
                if (messageInfo == null || Objects.equals(messageInfo, "")) {
                    throw new RuntimeException("redis列表中没有元素");
                }
                Message message = new Message();
                messageId = redisTemplate.opsForValue().increment("tb_process", 1).intValue();
                message.setId(messageId).setMessage(messageInfo).setCommitTime(new Date()).setThreadName(Thread.currentThread().getName());
                messageService.insert(message);
                // 消费;
                System.out.println("获取到列表中的值:" + messageInfo);
                Thread.sleep(3000);
            } catch (Exception e) {
                if (e instanceof QueryTimeoutException) {
                    // 超时异常不做处理;
                } else {
                    // 消息消费异常回滚;
                    if (messageInfo != null && Objects.equals(messageInfo, "")) {
                        redisTemplate.opsForList().leftPush(MessageEnum.INVOICE.getName(), messageInfo);
                    }
                }
            } finally {
                messageService.delete(messageId);
            }
        }
    }

}
