package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.PersonAccountCreateRequest;
import cn.esign.demo.base.provider.request.PersonAccountUpdateRequest;
import cn.esign.demo.base.provider.response.PersonAccountCreateResponse;
import cn.esign.demo.base.provider.response.PersonAccountResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 签署个人账号
 *
 * @author zhexiu
 * @since 2019/7/16 下午3:23
 */
public interface PersonAccountProvider {

    /**
     * 个人账号创建
     *
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/accounts/createByThirdPartyUserId")
    BaseResult<PersonAccountCreateResponse> accountCreate(PersonAccountCreateRequest request);


    /**
     * 个人账号修改
     *
     * @param accountId 账号id
     * @param request   请求参数
     * @return
     */
    @RequestLine("PUT /v1/accounts/{accountId}")
    BaseResult<PersonAccountResponse> accountUpdate(@Param("accountId") String accountId, PersonAccountUpdateRequest request);

    /**
     * 按照第三方用户ID修改
     *
     * @param thirdPartyUserId 第三方平台的用户唯一标识
     * @param request          请求参数
     * @return
     */
    @RequestLine("POST /v1/accounts/updateByThirdId?thirdPartyUserId={thirdPartyUserId}")
    BaseResult<PersonAccountResponse> accountUpdateByThirdId(@Param("thirdPartyUserId") String thirdPartyUserId,
                                                             PersonAccountUpdateRequest request);


    /**
     * 查询个人账号
     *
     * @param accountId 账号id
     * @return
     */
    @RequestLine("GET /v1/accounts/{accountId}")
    BaseResult<PersonAccountResponse> accountQuery(@Param("accountId") String accountId);


    /**
     * 按照第三方用户ID查询
     *
     * @param thirdPartyUserId 第三方平台的用户唯一标识
     * @return
     */
    @RequestLine("GET /v1/accounts/getByThirdId?thirdPartyUserId={thirdPartyUserId}")
    BaseResult<PersonAccountResponse> accountQueryByThirdId(@Param("thirdPartyUserId") String thirdPartyUserId);


    /**
     * 按照账号ID注销
     *
     * @param accountId 账号id
     * @return
     */
    @RequestLine("DELETE /v1/accounts/{accountId}")
    BaseResult accountDelete(@Param("accountId") String accountId);


    /**
     * 按照第三方用户ID注销
     *
     * @param thirdPartyUserId 第三方平台的用户唯一标识
     * @return
     */
    @RequestLine("DELETE /v1/accounts/deleteByThirdId?thirdPartyUserId={thirdPartyUserId}")
    BaseResult accountDeleteByThirdId(@Param("thirdPartyUserId") String thirdPartyUserId);
}



