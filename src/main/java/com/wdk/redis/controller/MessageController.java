package com.wdk.redis.controller;

import com.wdk.redis.config.ThreadPool;
import com.wdk.redis.entity.Message;
import com.wdk.redis.entity.MessageEnum;
import com.wdk.redis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: pengmin
 * @date: 2021/12/16 11:29
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private MessageService messageService;

    public static final Integer MAX_MESSAGE_CONSUMER_TIME = 60;

    @RequestMapping("/insert")
    public Object insertMessage(@RequestParam("message") String message) {
        redisTemplate.opsForList().leftPush(MessageEnum.INVOICE.getName(), message);
        System.out.println("新增消息:"  + message);
        return "ok";
    }

    @RequestMapping("/checkPendingMsg")
    public Object checkProcess() {
        List<Message> messageList= messageService.selectAll();
        if (!messageList.isEmpty()) {
            for (Message message : messageList) {
                if ((System.currentTimeMillis() - message.getCommitTime().getTime()) > MAX_MESSAGE_CONSUMER_TIME * 1000) {
                    redisTemplate.opsForList().leftPush(MessageEnum.INVOICE.getName(), message.getMessage());
                    messageService.delete(message.getId());
                }
            }
        }
        return "ok";
    }
}
