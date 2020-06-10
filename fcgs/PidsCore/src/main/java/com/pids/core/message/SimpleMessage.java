package com.pids.core.message;

import com.pids.core.utils.StringEx;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.LoggerFactory;

public class SimpleMessage implements Message {
    private String message;
    private String msgType;
    private Object msgData;

    public SimpleMessage() {
        super();
    }

    public SimpleMessage(String message) {
        super();
        this.message = message;
    }

    public SimpleMessage(String message, String msgType) {
        super();
        this.message = message;
        this.msgType = msgType;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @Override
    public String getMsgType() {
        return msgType;
    }

    @Override
    public Object getMsgData() {
        return msgData;
    }

    @Override
    public void setMsgData(Object msgData) {
        this.msgData = msgData;
    }

    @Override
    public void addMessage(String message) {
        if (StringEx.isNull(message)) {
            return;
        }
        if (StringEx.isNull(this.message)) {
            this.message = message;
        }
        this.message += "\n" + message;
    }

    @Override
    public void setMessage(Message message) {
        if (message == null) {
            return;
        }
        try {
            BeanUtils.copyProperties(this, message);
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).warn("{}对象拷贝失败！", message.getClass().getName());
        }
    }

    @Override
    public boolean hasMessage() {
        return !StringEx.isNull(this.message);
    }
}
