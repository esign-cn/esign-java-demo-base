package cn.esign.demo.base.provider.request;

import lombok.Data;

/**
 * 静默签署授权请求
 * @author zhexiu
 * @since 2019/7/19 下午6:03
 */
@Data
public class SignAuthRequest {

    /**
     * 授权截止时间, 格式为yyyy-MM-dd HH:mm:ss，默认无限期
     */
   private Long deadline;

}
