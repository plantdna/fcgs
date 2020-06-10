package com.pids.core.message;

/**
 * 消息接口
 *
 * @author Andory
 * @date 2013-6-25下午3:25:43
 */
public interface Message {
    /**
     * @fields ERROR_MSG 错误消息
     */
    public String ERROR_MSG = "ERROR";
    /**
     * @fields SUCESS_MSG 操作成功消息
     */
    public String SUCESS_MSG = "SUCCESS";
    /**
     * @fields FILE_MSG 文件消息
     */
    public String FILE_MSG = "FILE";

    /**
     * 设置消息
     *
     * @param message
     * @author Andory
     * @date 2013-6-25下午3:25:54
     */
    public void setMessage(String message);

    /**
     * 获取消息
     *
     * @return
     * @author Andory
     * @date 2013-6-25下午3:25:55
     */
    public String getMessage();

    /**
     * 获取消息类型值
     *
     * @return
     * @author Andory
     * @date 2013-6-25下午3:33:27
     */
    public abstract String getMsgType();

    /**
     * 设置消息类型值
     *
     * @param msgType
     * @author Andory
     * @date 2013-6-25下午3:33:29
     */
    public abstract void setMsgType(String msgType);

    /**
     * 获取消息数据
     *
     * @return java.lang.Object
     * @author jiangbin
     * @date 2019-08-10 16:25
     **/
    public Object getMsgData();

    /**
     * 设置消息数据
     *
     * @param msgData
     * @return void
     * @author jiangbin
     * @date 2019-08-10 16:25
     **/
    public void setMsgData(Object msgData);

    /**
     * 添加消息，新添加的消息将附加在原消息后面，并以换行符结尾
     *
     * @param message
     * @author Andory
     * @date 2013-6-25下午3:36:39
     */
    public abstract void addMessage(String message);

    /**
     * 设置消息信息对象
     *
     * @param message
     * @author Andory
     * @date 2013-6-25下午3:41:48
     */
    public abstract void setMessage(Message message);

    /**
     * 判断是否存在消息内容
     *
     * @return
     * @author Andory
     * @date 2013-6-25下午3:43:57
     */
    public abstract boolean hasMessage();
}
