package com.milktea.milkteauser.vo;

public class MessageType {
    
    //消息类型 0：历史订单 1：新订单 2：更新订单 3：订单完成  4：半小时提醒 5：撤单  
    private String messageType;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    
}
