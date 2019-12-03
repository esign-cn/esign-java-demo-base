package cn.esign.demo.base.client;

import cn.esign.demo.base.auth.AuthCenter;

/**
 * 启动客户端
 * @author zhexiu
 * @since 2019/7/22 下午4:17
 */
public class EsignClient {


    public static final Environment ENV = new Environment();

    public void build() {
        ENV.load();
        new AuthCenter(ENV).start();
    }

}
