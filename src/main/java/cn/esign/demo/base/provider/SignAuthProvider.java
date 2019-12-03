package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.SignAuthRequest;
import feign.Param;
import feign.RequestLine;

/**
 * 签署授权
 *
 * @author zhexiu
 * @since 2019/7/21 下午12:40
 */
public interface SignAuthProvider {

    /**
     * 静默签署授权
     *
     * @param accountId 授权人id，即个人账号id或机构账号id
     * @param request   请求参数
     * @return 是否成功 true/false
     */
    @RequestLine("POST /v1/signAuth/{accountId}")
    BaseResult<Boolean> signAuth(@Param("accountId") String accountId, SignAuthRequest request);


    /**
     * 取消静默签署授权
     *
     * @param accountId 授权人id，即个人账号id或机构账号id
     * @return 是否成功 true/false
     */
    @RequestLine("DELETE /v1/signAuth/{accountId}")
    BaseResult<Boolean> signAuthCancel(@Param("accountId") String accountId);
}
