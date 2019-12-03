package cn.esign.demo.base.http;

import cn.esign.demo.base.auth.AuthCenter;
import cn.esign.demo.base.cache.Cache;
import cn.esign.demo.base.cache.SimpleCache;
import cn.esign.demo.base.client.EsignClient;
import cn.esign.demo.base.http.config.HttpConstant;
import cn.esign.demo.base.model.AccessToken;
import cn.esign.demo.base.utils.Assert;
import com.alibaba.fastjson.JSON;
import feign.Feign;
import feign.Feign.Builder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OkHttp3Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(OkHttp3Client.class);
    private static final Cache<String, Object> CLASS_CACHE = new SimpleCache();

    public OkHttp3Client() {
    }

    public static <T> T getApi(Class<T> clazz) {
        return getApi(clazz, true);
    }

    public static <T> T getApi(Class<T> clazz, boolean isHeader) {
        Assert.isNotNull(clazz);
        String key = clazz.getName() + "." + isHeader;
        Object clazzCache = CLASS_CACHE.get(key);
        if (clazzCache != null) {
            return (T)clazzCache;
        }

        T t;
        synchronized (clazz){
            clazzCache = CLASS_CACHE.get(key);
            if (clazzCache != null) {
                return (T)clazzCache;
            }
            Builder builder = Feign.builder();
            builder.decoder(new GsonDecoder());
            builder.encoder(new GsonEncoder());
            if (isHeader) {
                builder.requestInterceptor(new OkHttp3Client.ForwardedForInterceptor());
            }

            builder.client(new OkHttpClient());
            t = builder.target(clazz, EsignClient.ENV.getOpenapi());
            CLASS_CACHE.put(key, t);
        }

        return t;
    }

    static class ForwardedForInterceptor implements RequestInterceptor {
        ForwardedForInterceptor() {
        }

        @Override
        public void apply(RequestTemplate template) {
            AccessToken token = AuthCenter.ACCESS_TOKEN_DEFAULT.get();
            template.header("Content-Type", new String[]{HttpConstant.JSON});
            template.header("X-Tsign-Open-App-Id", new String[]{token.getAppId()});
            template.header("X-Tsign-Open-Token", new String[]{token.getToken()});
            LOGGER.info("http template header,{}", JSON.toJSONString(template.headers()));
        }
    }
}
