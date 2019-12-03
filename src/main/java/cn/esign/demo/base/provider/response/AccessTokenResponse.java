package cn.esign.demo.base.provider.response;

import lombok.Data;

/**
 * 鉴权Token信息
 * @author zhexiu
 * @since 2019/7/16 下午5:20
 */
@Data
public class AccessTokenResponse {

    /**
     * 授权码
     */
    private String token;

    /**
     * 有效截止时间（毫秒）
     */
    private long expiresIn;




}
