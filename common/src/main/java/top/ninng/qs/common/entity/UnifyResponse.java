package top.ninng.qs.common.entity;

import top.ninng.qs.common.constants.Constants;

import java.io.Serializable;

/**
 * 统一响应
 *
 * @Author OhmLaw
 * @Date 2022/12/29 16:20
 * @Version 1.0
 */
public class UnifyResponse<T> implements Serializable {

    private static final long serialVersionUID = -2947981031157449864L;

    public static int SUCCESS = Constants.SUCCESS;
    public static int FAIL = Constants.FAIL;
    /**
     * 响应码
     */
    private int code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public UnifyResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> UnifyResponse<T> ok() {
        return result(SUCCESS, "", null);
    }

    public static <T> UnifyResponse<T> ok(T data) {
        return result(SUCCESS, "", data);
    }

    public static <T> UnifyResponse<T> ok(String message, T data) {
        return result(SUCCESS, message, data);
    }

    public static <T> UnifyResponse<T> fail() {
        return result(FAIL, "", null);
    }

    public static <T> UnifyResponse<T> fail(T data) {
        return result(FAIL, "", data);
    }

    public static <T> UnifyResponse<T> fail(String message, T data) {
        return result(FAIL, message, data);
    }

    public static <T> UnifyResponse<T> fail(int code, String message, T data) {
        return result(code, message, data);
    }

    private static <T> UnifyResponse<T> result(int code, String message, T data) {
        return new UnifyResponse<T>(code, message, data);
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
