package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.OrganizationAccountCreateRequest;
import cn.esign.demo.base.provider.request.OrganizationAccountUpdateRequest;
import cn.esign.demo.base.provider.response.OrganizationAccountCreateResponse;
import cn.esign.demo.base.provider.response.OrganizationAccountResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 签署企业账号
 *
 * @author zhexiu
 * @since 2019/7/16 下午3:23
 */
public interface OrganizationAccountProvider {

    /**
     * 企业账号创建
     *
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/organizations/createByThirdPartyUserId")
    BaseResult<OrganizationAccountCreateResponse> accountCreate(OrganizationAccountCreateRequest request);


    /**
     * 企业账号修改
     *
     * @param orgId   机构账号id
     * @param request 请求参数
     * @return
     */
    @RequestLine("PUT /v1/organizations/{orgId}")
    BaseResult<OrganizationAccountResponse> accountUpdate(@Param("orgId") String orgId, OrganizationAccountUpdateRequest request);

    /**
     * 按照第三方用户ID修改
     *
     * @param thirdPartyUserId 第三方平台机构id
     * @param request          请求参数
     * @return
     */
    @RequestLine("POST /v1/organizations/updateByThirdId?thirdPartyUserId={thirdPartyUserId}")
    BaseResult<OrganizationAccountResponse> accountUpdateByThirdId(@Param("thirdPartyUserId") String thirdPartyUserId, OrganizationAccountUpdateRequest request);


    /**
     * 查询企业账号
     *
     * @param orgId 机构账号id
     * @return
     */
    @RequestLine("GET /v1/organizations/{orgId}")
    BaseResult<OrganizationAccountResponse> accountQuery(@Param("orgId") String orgId);


    /**
     * 按照第三方用户ID查询
     *
     * @param thirdPartyUserId 第三方平台机构id
     * @return
     */
    @RequestLine("GET /v1/organizations/getByThirdId?thirdPartyUserId={thirdPartyUserId}")
    BaseResult<OrganizationAccountResponse> accountQueryByThirdId(@Param("thirdPartyUserId") String thirdPartyUserId);


    /**
     * 按照账号ID注销
     *
     * @param orgId 机构账号id
     * @return
     */
    @RequestLine("DELETE /v1/organizations/{orgId}")
    BaseResult accountDelete(@Param("orgId") String orgId);


    /**
     * 按照第三方用户ID注销
     *
     * @param thirdPartyUserId 第三方平台机构id
     * @return
     */
    @RequestLine("DELETE /v1/organizations/deleteByThirdId?thirdPartyUserId={thirdPartyUserId}")
    BaseResult accountDeleteByThirdId(@Param("thirdPartyUserId") String thirdPartyUserId);
}



