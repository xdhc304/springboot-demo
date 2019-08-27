package com.xdhc.demo.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = 2112958571804984420L;

    // 主键ID
    private Integer id;
    // 名称
    private String name;
    // 存储消息发送的唯一标识
    private String messageId;

    public Order() {
        super();
    }

    public Order(Integer id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
