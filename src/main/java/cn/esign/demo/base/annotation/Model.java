package cn.esign.demo.base.annotation;

import java.lang.annotation.*;

/**
 * 常量名称
 *
 * @author zhexiu
 * @since 2019/7/19 上午11:01
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Model {

    /**
     * The actual value expression: e.g. "#{systemProperties.myProp}".
     *
     * @return
     */
    String value();
}
