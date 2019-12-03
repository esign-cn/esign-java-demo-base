package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.SignfieldAutoSignBatchAddRequest;
import cn.esign.demo.base.provider.request.SignfieldHandSignBatchAddRequest;
import cn.esign.demo.base.provider.request.SignfieldPlatformBatchAddRequest;
import cn.esign.demo.base.provider.response.SignfieldAddResponse;
import cn.esign.demo.base.provider.response.SignfieldBatchDeleteResponse;
import cn.esign.demo.base.provider.response.SignfieldQueryResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 签署流程签署区管理
 *
 * @author zhexiu
 * @since 2019/7/18 上午8:54
 */
public interface FlowSignFieldProvider {


    /**
     * 查询签署区列表
     *
     * @param flowId       流程id
     * @param accountId    账号id，默认所有签署人
     * @param signfieldIds 指定签署区id列表，逗号分割，默认所有签署区
     * @return
     */
    @RequestLine("GET /v1/signflows/{flowId}/signfields?accountId={accountId}&signfieldIds={signfieldIds}")
    BaseResult<SignfieldQueryResponse> querySignfields(@Param("flowId") String flowId,
                                                       @Param("accountId") String accountId,
                                                       @Param("signfieldIds") String signfieldIds);


    /**
     * 添加平台自动盖章签署区
     *
     * @param flowId  流程id
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/signflows/{flowId}/signfields/platformSign")
    BaseResult<SignfieldAddResponse> platformDoSign(@Param("flowId") String flowId,
                                                    SignfieldPlatformBatchAddRequest request);


    /**
     * 添加签署方自动盖章签署区
     *
     * @param flowId  流程id
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/signflows/{flowId}/signfields/autoSign")
    BaseResult<SignfieldAddResponse> autoDoSign(@Param("flowId") String flowId,
                                                SignfieldAutoSignBatchAddRequest request);


    /**
     * 添加手动盖章签署区
     *
     * @param flowId  流程id
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/signflows/{flowId}/signfields/handSign")
    BaseResult<SignfieldAddResponse> handDosign(@Param("flowId") String flowId,
                                                SignfieldHandSignBatchAddRequest request);


    /**
     * 删除签署区
     *
     * @param flowId       流程id
     * @param signfieldIds 签署区id列表，逗号分割
     * @return
     */
    @RequestLine("DELETE /v1/signflows/{flowId}/signfields?signfieldIds={signfieldIds}")
    BaseResult deleteSignfields(@Param("flowId") String flowId,
                                @Param("signfieldIds") String signfieldIds);

}
