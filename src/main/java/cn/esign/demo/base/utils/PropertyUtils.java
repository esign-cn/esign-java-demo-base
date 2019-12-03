package cn.esign.demo.base.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件加载
 */
public class PropertyUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyUtils.class);
    public static final String FILE_SEP  = File.separator;;

    public PropertyUtils() {
    }


    public static Properties loadByPath(String path) {
        Properties p = new Properties();

        try {
            InputStream in = PropertyUtils.class.getResourceAsStream(path);
            if(in == null){
                String classPathPath = "classpath:"+path;
                in = ResourceUtils.getURL(classPathPath).openStream();
            }
            if(in == null){
                LOGGER.warn("can not load path:{}", path);
                return p;
            }
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;
    }

    public static Properties loadByName(String name) {
        Properties p = new Properties();

        try {
            InputStream in = findClassLoader().getResourceAsStream(name);
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return p;
    }



    public static ClassLoader findClassLoader() {
        return ClassUtils.getClassLoader();
    }


}
