package com.wdk.redis.mapper;

import com.wdk.redis.entity.Message;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Date;
import java.util.List;

/**
 * @author: pengmin
 * @date: 2021/12/16 14:03
 */
public interface MessageMapper {

    void insert(Message message);

    void deleteById(@Param("id") Integer id);

    List<Message> selectAll();
}
