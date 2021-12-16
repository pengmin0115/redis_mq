package com.wdk.redis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: pengmin
 * @date: 2021/12/16 14:23
 */
public class Message implements Serializable {

    private Integer id;

    private String threadName;

    private String message;

    private Date commitTime;

    public Integer getId() {
        return id;
    }

    public Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getThreadName() {
        return threadName;
    }

    public Message setThreadName(String threadName) {
        this.threadName = threadName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Message setMessage(String message) {
        this.message = message;
        return this;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public Message setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
        return this;
    }
}
