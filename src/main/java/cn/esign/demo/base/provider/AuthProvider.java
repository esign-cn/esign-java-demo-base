package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.response.AccessTokenResponse;
import feign.Param;
import feign.RequestLine;

/**
 * API鉴权
 *
 * @author zhexiu
 * @since 2019/7/16 下午3:23
 */
public interface AuthProvider {

    /**
     * 获取鉴权Token
     *
     * @param appId     应用id，需在e签宝开放平台创建
     * @param secret    应用密钥，不可泄露
     * @param grantType 授权类型，固定值: client_credentials
     * @return
     */
    @RequestLine("GET /v1/oauth2/access_token?appId={appId}&secret={secret}&grantType={grantType}")
    BaseResult<AccessTokenResponse> accessToken(@Param("appId") String appId, @Param("secret") String secret, @Param("grantType") String grantType);

}
