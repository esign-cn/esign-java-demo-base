package cn.esign.demo.base.extension;

import java.lang.annotation.*;

/**
 * Marker for extension interface
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * default extension name
     */
    String value() default "";

}