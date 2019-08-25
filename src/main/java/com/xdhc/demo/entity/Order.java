package com.xdhc.demo.entity;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = 2112958571804984420L;

    // 主键ID
    private Integer id;
    // 名称
    private String name;
    // 存储消息发送的唯一标识
    private Integer messageId;

    public Order() {
        super();
    }

    public Order(Integer id, String name, Integer messageId) {
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

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
