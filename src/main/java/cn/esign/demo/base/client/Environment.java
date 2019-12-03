package cn.esign.demo.base.client;

import cn.esign.demo.base.annotation.Model;
import cn.esign.demo.base.utils.ModelObjectUtils;
import cn.esign.demo.base.utils.PropertyUtils;
import cn.esign.demo.base.utils.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 环境配置信息 */
public class Environment {
    private static final Logger LOGGER = LoggerFactory.getLogger(Environment.class);
    private static final String BASE_CONFIG = "esign.properties";
    private static final String ENV_CONFIG = "env.properties";
    private static final String ENV = "env";
    private static final String APP_ID = "appId";
    private static final String APP_SECRET = "appSecret";
    private static final String TOKEN_PERIOD = "tokenPeriod";

    @Model(ENV)
    private String env;

    @Model("openapi")
    private String openapi;

    @Model(APP_ID)
    private String appId;

    @Model(APP_SECRET)
    private String appSecret;

    @Model(TOKEN_PERIOD)
    private int tokenPeriod;

    public Environment() {}

    public String getEnv() {
        return this.env;
    }

    public String getOpenapi() {
        return this.openapi;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppSecret() {
        return this.appSecret;
    }

    public int getTokenPeriod() {
        return this.tokenPeriod;
    }

    /**
     * 加载配置，顺序如下
     * 1. 加载公共配置
     * 2. 加载用户配置文件
     * 3. 加载JVM 自定义配置
     */
    public void load() {
        Map<String, String> envMap = new HashMap<>();
        String env = System.getProperty(ENV);
        envMap.putAll(loadBase());
        if (StringUtils.isBlank(env)) {
            env = envMap.get(ENV);
        }
        if (StringUtils.isBlank(env)) {
            throw new RuntimeException("plz config env!!!");
        }
        envMap.putAll(loadEnvConfig(env));
        envMap.putAll(loadJVMProp());
        LOGGER.info("LOADING config");
        for (Entry<String, String> entry : envMap.entrySet()) {
            LOGGER.info("config:{}, value={}", entry.getKey(), entry.getValue());
        }

        try {
            ModelObjectUtils.fill(this, envMap);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加载公共配置
     * @return
     */
    private Map<String, String> loadBase() {
        Properties baseProp = PropertyUtils.loadByName(BASE_CONFIG);
        return loadProp(baseProp);
    }

    /**
     * 加载用户配置文件
     * @param env
     * @return
     */
    private Map<String, String> loadEnvConfig(String env) {
        env = env.toLowerCase();
        String envPropFile =
                ENV + PropertyUtils.FILE_SEP + env + PropertyUtils.FILE_SEP + ENV_CONFIG;
        Properties envProp = PropertyUtils.loadByPath(envPropFile);
        return loadProp(envProp);
    }

    /**
     * 加载JVM 自定义配置
     * @return
     */
    private Map<String, String> loadJVMProp() {
        String[] keys = {ENV, APP_ID, APP_SECRET};
        Map<String, String> envMap = new HashMap(keys.length);
        for (String key : keys) {
            String val = System.getProperty(key);
            if (StringUtils.isBlank(val)) {
                continue;
            }
            envMap.put(key, val);
        }
        return envMap;
    }

    /**
     * 加载Properties文件信息
     * @param envProp
     * @return
     */
    private Map<String, String> loadProp(Properties envProp) {
        Map<String, String> envMap = new HashMap(8);
        for (String name : envProp.stringPropertyNames()) {
            String value = envProp.getProperty(name);
            if (StringUtils.isBlank(value)) {
                continue;
            }
            envMap.put(name, value);
        }
        return envMap;
    }
}
