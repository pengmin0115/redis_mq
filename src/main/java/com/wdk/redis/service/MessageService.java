package com.wdk.redis.service;

import com.wdk.redis.entity.Message;

import java.util.List;

/**
 * @author: pengmin
 * @date: 2021/12/16 14:02
 */
public interface MessageService {

    void insert(Message message);

    void delete(Integer messageId);

    List<Message> selectAll();
}
