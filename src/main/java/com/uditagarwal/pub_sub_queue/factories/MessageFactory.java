package com.uditagarwal.pub_sub_queue.factories;

import com.uditagarwal.pub_sub_queue.models.Message;

public class MessageFactory {
    public static Message getNewMessage(String message) {
        return new Message(message);
    }
}
