package com.example.solamly.basemodule.util.eventbus;

/**
 * Created by lonel on 2018/3/27.
 */

public class EventBusMessage<T> {
    public EventBusMessage(int type, T message) {
        this.type = type;
        this.message = message;
    }

    public EventBusMessage(int type) {
        this.type = type;
    }


    public int type;
    public T message;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "EventBusMessage{" +
            "type=" + type +
            ", message=" + message +
            '}';
    }
}
