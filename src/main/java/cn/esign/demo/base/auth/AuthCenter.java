package cn.esign.demo.base.auth;

import cn.esign.demo.base.client.Environment;
import cn.esign.demo.base.constants.AuthType;
import cn.esign.demo.base.http.OkHttp3Client;
import cn.esign.demo.base.model.AccessToken;
import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.model.ResultSupport;
import cn.esign.demo.base.provider.AuthProvider;
import cn.esign.demo.base.provider.response.AccessTokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 授权管理中心
 *
 * @author zhexiu
 * @date
 */
public class AuthCenter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCenter.class);

    /** 默认存储周期 */
    private static final int PERIOD_DEFAULT = 3600;

    private final ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(1);

    private Environment environment;
    public static Map<String, AccessToken> ACCESS_TOKEN_MAP = new ConcurrentHashMap<>();
    public static AtomicReference<AccessToken> ACCESS_TOKEN_DEFAULT = new AtomicReference<>();

    public AuthCenter(Environment environment) {
        this.environment = environment;
    }

    public void start() {
        int period = PERIOD_DEFAULT;
        if (this.environment.getTokenPeriod() > 0) {
            period = this.environment.getTokenPeriod();
        }

        this.initToken();
        this.scheduler.scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            AuthCenter.this.initToken();
                        } catch (Exception e) {
                            LOGGER.error(" AuthCenter refreshToken fail,{}", e.getMessage());
                        }
                    }
                },
                (long) period,
                (long) period,
                TimeUnit.SECONDS);
    }

    private void initToken() {
        String appId = this.environment.getAppId();
        String appSecret = this.environment.getAppSecret();
        AuthProvider authProvider = OkHttp3Client.getApi(AuthProvider.class, false);
        BaseResult<AccessTokenResponse> result =
                authProvider.accessToken(
                        appId, appSecret, AuthType.CLIENT_CREDENTIALS.getGrantType());
        if (ResultSupport.isFail(result)) {
            LOGGER.error("initToken, error,{}", result.getMessage());
            throw new RuntimeException("initToken, error="+result.getMessage());
        } else {
            AccessTokenResponse resultData = result.getData();
            AccessToken token = new AccessToken();
            token.setAppId(appId);
            token.setToken(resultData.getToken());
            token.setExpiresIn(resultData.getExpiresIn());
            ACCESS_TOKEN_DEFAULT.set(token);

            LOGGER.info("refreshToken,{}", this.environment.getAppId());
        }
    }
}
