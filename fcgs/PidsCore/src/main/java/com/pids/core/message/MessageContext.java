package com.pids.core.message;

import com.pids.core.threadlocal.ThreadLocalRegister;
import org.apache.commons.lang3.StringUtils;

/**
 * 消息上下文信息对象，若存在需要返回到前端的错误信息或提示信息则可向该上下文容器中添加,
 * 消息信息对象是与线程绑定的，并且消息可以在子线程中设置或获取
 *
 * @author jiang
 * @date 2019年6月22日下午9:48:36
 */
public class MessageContext {
    private static ThreadLocal<Message> message_tl = ThreadLocalRegister.regist();

    /**
     * 获取消息内容
     *
     * @return
     * @author jiang
     * @date 2019年6月22日下午9:53:57
     */
    public static Message getMessage() {
        return message_tl.get();
    }

    /**
     * 获取消息内容
     *
     * @return
     * @author jiang
     * @date 2019年6月22日下午9:53:57
     */
    public static String getMessageContent() {
        Message message = message_tl.get();
        return message == null ? null : message.getMessage();
    }

    /**
     * 获取消息类型
     *
     * @return
     * @author jiang
     * @date 2019年6月22日下午11:24:52
     */
    public static String getMessageType() {
        Message message = message_tl.get();
        return message == null ? null : message.getMsgType();
    }

    /**
     * <pre>判定当前消息类型是否与给定类型相同:
     * 1、无消息内容则直接返回false
     * 2、未给定待检测消息类型则返回false
     * 3、未设置消息类型则返回false
     * 4、检测待检测消息类型与消息类型是否一致</pre>
     *
     * @param sMsgType 消息类型
     * @return true/false--相同/不同
     * @author jiang
     * @date 2019年6月22日下午11:29:51
     */
    public static boolean isMsgType(String sMsgType) {
        if (!hasMessage()) {
            return false;
        }
        if (StringUtils.isBlank(sMsgType)) {
            return false;
        }
        return StringUtils.equalsIgnoreCase(sMsgType, getMessageType());
    }

    /**
     * 设置消息内容
     *
     * @param message
     * @author jiang
     * @date 2019年6月22日下午9:54:00
     */
    public static void setMessage(Message message) {
        message_tl.set(message);
    }

    /**
     * 设置错误消息内容
     *
     * @param message
     * @author jiang
     * @date 2019年6月22日下午9:54:00
     */
    public static void error(String message) {
        message_tl.set(new SimpleMessage(message, Message.ERROR_MSG));
    }

    /**
     * 设置成功消息内容
     *
     * @param message
     * @author jiang
     * @date 2019年6月22日下午9:54:00
     */
    public static void success(String message) {
        message_tl.set(new SimpleMessage(message, Message.SUCESS_MSG));
    }

    /**
     * 设置消息内容
     *
     * @param message
     * @author jiang
     * @date 2019年6月22日下午9:54:00
     */
    public static void setMessage(String message) {
        message_tl.set(new SimpleMessage(message));
    }

    /**
     * 设置消息内容
     *
     * @param message 消息内容
     * @param msgType 消息类型
     * @author jiang
     * @date 2019年6月22日下午9:54:00
     */
    public static void setMessage(String message, String msgType) {
        message_tl.set(new SimpleMessage(message, msgType));
    }

    /**
     * 获取消息数据
     *
     * @return void
     * @author jiangbin
     * @date 2019-08-10 16:28
     **/
    public static Object getData() {
        Message message = getMessage();
        return message == null ? null : message.getMsgData();
    }

    /**
     * 设置消息内容
     *
     * @param data 消息数据
     * @author jiang
     * @date 2019年6月22日下午9:54:00
     */
    public static void setData(Object data) {
        Message message = getMessage();
        if (message == null) {
            message = new SimpleMessage();
        }
        message.setMsgData(data);
        message_tl.set(message);
    }

    /**
     * 清空消息内容
     *
     * @author jiang
     * @date 2019年6月22日下午9:54:04
     */
    public static void clear() {
        message_tl.remove();
    }

    /**
     * 是否存在消息内容
     *
     * @return
     * @author jiang
     * @date 2019年6月22日下午11:33:46
     */
    public static boolean hasMessage() {
        return getMessage() != null && StringUtils.isNotBlank(getMessage().getMessage());
    }

    /**
     * 是否存在消息数据
     *
     * @return
     * @author jiang
     * @date 2019年6月22日下午11:33:46
     */
    public static boolean hasMsgData() {
        return getMessage() != null && getMessage() != null;
    }
}
