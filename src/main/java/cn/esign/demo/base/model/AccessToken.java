package cn.esign.demo.base.model;

import lombok.Data;

/**
 * token信息
 *
 * @author zhexiu
 * @since 2019/7/19 下午4:15
 */
@Data
public class AccessToken {

    /**
     * 项目ID
     */
    private String appId;

    /**
     * 授权 token
     */
    private String token;

    /**
     * token有效期
     */
    private Long expiresIn;


}
