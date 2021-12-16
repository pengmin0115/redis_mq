package com.wdk.redis.entity;

/**
 * @author: pengmin
 * @date: 2021/12/16 16:21
 */
public enum  MessageEnum {
    /**
     * 普通电子发票;
     */
    INVOICE("list:invoice:ordinaryInvoice");

    private String name;

    MessageEnum(String name) {
        this.name = name;
    }

    MessageEnum() {
    }

    public String getName() {
        return name;
    }

    public MessageEnum setName(String name) {
        this.name = name;
        return this;
    }
}
