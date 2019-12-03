package cn.esign.demo.base.model;

import java.util.Formatter;

/**
 * @author zhexiu
 * @since 2019/7/22 下午8:33
 */
public enum ResultEnum {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 部分失败
     */
    PARTIAL_FAILURE(1, "部分失败"),

    /**
     * 登录超时
     */
    UNAUTH(401, "登录超时"),

    /**
     * 参数错误
     */
    PARAM_WRONG(100000, "参数错误"),

    /**
     * 内部服务错误
     */
    SERVER_TEMPLATE_ERR(100010, "内部服务错误"),

    /**
     * 内部服务错误
     */
    RPC_INVOKE_ERR(100011, "内部服务错误");

    private int code;

    private String msg;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public String format(String... content) {

        Formatter e = null;

        try {
            e = new Formatter();
            return e.format(this.msg, content).toString();
        } finally {
            if (null != e) {
                e.close();
            }
        }
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

