package cn.esign.demo.base.utils;

import java.util.UUID;

/**
 * @author zhexiu
 * @since 2018/4/16 下午6:49
 */
public class UUIDUtils {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
