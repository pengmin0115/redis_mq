package com.wdk.redis.service.impl;

import com.wdk.redis.entity.Message;
import com.wdk.redis.mapper.MessageMapper;
import com.wdk.redis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author: pengmin
 * @date: 2021/12/16 14:02
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void insert(Message message) {
        messageMapper.insert(message);
    }

    @Override
    public void delete(Integer messageId) {
        messageMapper.deleteById(messageId);
    }

    @Override
    public List<Message> selectAll() {
        return messageMapper.selectAll();
    }
}
