package cn.esign.demo.base.http.config;

import java.nio.charset.Charset;

/**
 * 协议常量
 */
public class HttpConstant {
    public static final String JSON = "application/json";
    public static final String FORM = "application/x-www-form-urlencoded";
    public static final String STREAM = "application/octet-stream";
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_MD5 = "Content-MD5";
    public static final Charset CHARSET = Charset.forName("UTF-8");

    public HttpConstant() {
    }
}
