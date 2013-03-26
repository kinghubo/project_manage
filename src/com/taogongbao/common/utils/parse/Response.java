package com.taogongbao.common.utils.parse;

/**
 * 通过泛型返回请求的响应.这里只返回单个对象和java基本类型的情况。
 *
 * @createTime: Nov 18, 2010 8:30:58 PM
 * @author: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @version: 0.1
 * @lastVersion: 0.1
 * @updateTime: 
 * @updateAuthor: <a href="mailto:gaoxuguo@feinno.com">Xuguo Gao</a>
 * @changesSum: 
 * 
 */
public class Response<T> {
    /** 返回状态码 */
    private int code;
    /** 返回错误消息 **/
    private String message = "";
    /** 返回的结果对象 */
    private T data;
    /** sessionId */
    private String sessionId = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Response [code=" + code + ", data=" + data + ", message=" + message + ", sessionId=" + sessionId + "]";
    }

}
