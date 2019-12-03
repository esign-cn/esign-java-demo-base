package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.CreateImageSealRequest;
import cn.esign.demo.base.provider.request.CreateOrganizationTemplateSealRequest;
import cn.esign.demo.base.provider.request.CreatePersonTemplateSealRequest;
import cn.esign.demo.base.provider.response.CreateSealResponse;
import cn.esign.demo.base.provider.response.QuerySealResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 印章管理
 *
 * @author zhexiu
 * @since 2019/7/21 下午2:53
 */
public interface SealProvider {

    /**
     * 创建个人模板印章
     *
     * @param accountId 用户id
     * @param request   请求参数
     * @return
     */
    @RequestLine("POST /v1/accounts/{accountId}/seals/personaltemplate")
    BaseResult<CreateSealResponse> createPersonTemplateSeal(@Param("accountId") String accountId,
                                                            CreatePersonTemplateSealRequest request);

    /**
     * 查询个人印章
     *
     * @param accountId 用户id
     * @param offset    分页起始位置
     * @param size      单页数量
     * @return
     */
    @RequestLine("GET /v1/accounts/{accountId}/seals?offset={offset}&size={size}")
    BaseResult<QuerySealResponse> queryPersonSeals(@Param("accountId") String accountId,
                                                   @Param("offset") Integer offset,
                                                   @Param("size") Integer size);


    /**
     * 创建机构模板印章
     *
     * @param orgId   机构ID
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/organizations/{orgId}/seals/officialtemplate")
    BaseResult<CreateSealResponse> createOrganizationTemplateSeal(@Param("orgId") String orgId, CreateOrganizationTemplateSealRequest request);


    /**
     * 查询机构印章
     *
     * @param orgId  机构ID
     * @param offset 分页起始位置
     * @param size   单页数量
     * @return
     */
    @RequestLine("GET /v1/organizations/{orgId}/seals?offset={offset}&size={size}")
    BaseResult<QuerySealResponse> queryOrganizationSeals(@Param("orgId") String orgId,
                                                         @Param("offset") Integer offset,
                                                         @Param("size") Integer size);


    /**
     * 创建个人/机构图片印章
     *
     * @param accountId 用户id
     * @param request   请求参数
     * @return
     */
    @RequestLine("POST /v1/accounts/{accountId}/seals/image")
    BaseResult<CreateSealResponse> createImageSeal(@Param("accountId") String accountId,
                                                   CreateImageSealRequest request);


    /**
     * 删除个人印章
     *
     * @param accountId
     * @param sealId
     * @return
     */
    @RequestLine("DELETE /v1/accounts/{accountId}/seals/{sealId}")
    BaseResult deletePersonSeal(@Param("accountId") String accountId,
                                @Param("sealId") String sealId);


    /**
     * 删除机构印章
     *
     * @param orgId  机构id
     * @param sealId 印章id
     * @return
     */
    @RequestLine("DELETE /v1/organizations/{orgId}/seals/{sealId}")
    BaseResult deleteOrganizationSeals(@Param("orgId") String orgId,
                                       @Param("sealId") String sealId);

}
