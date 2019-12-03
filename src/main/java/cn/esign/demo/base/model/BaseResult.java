package cn.esign.demo.base.model;

/**
 * 通用返回结果
 *
 * @author zhexiu
 * @since 2019/7/16 下午5:16
 */
public class BaseResult<T> {

    /**
     * 错误码
     */
    private int code;


    /**
     * 错误信息
     */
    private String message;


    /**
     * 业务信息
     */
    private T data;


    public BaseResult() {
    }

    public BaseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResult(ResultEnum resultEnum, T data) {
        this.code = resultEnum.getCode();
        this.message = resultEnum.getMsg();
        this.data = data;
    }


    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static BaseResult success() {
        return new BaseResult(ResultEnum.SUCCESS, null);
    }

    public static <T> BaseResult success(T data) {
        return new BaseResult(ResultEnum.SUCCESS, data);
    }
}

