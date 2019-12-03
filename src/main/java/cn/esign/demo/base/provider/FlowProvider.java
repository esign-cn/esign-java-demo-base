package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.FlowCreateRequest;
import cn.esign.demo.base.provider.request.FlowRevokeRequest;
import cn.esign.demo.base.provider.response.FlowCreateResponse;
import cn.esign.demo.base.provider.response.FlowQueryResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 签署流程管理
 *
 * @author zhexiu
 * @since 2019/7/18 上午8:54
 */
public interface FlowProvider {


    /**
     * 签署流程创建
     *
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/signflows")
    BaseResult<FlowCreateResponse> createFlow(FlowCreateRequest request);


    /**
     * 签署流程查询
     *
     * @param flowId 流程id
     * @return
     */
    @RequestLine("GET v1/signflows/{flowId}")
    BaseResult<FlowQueryResponse> queryFlow(@Param("flowId") String flowId);


    /**
     * 签署流程开启
     *
     * @param flowId 流程id
     * @return
     */
    @RequestLine("PUT /v1/signflows/{flowId}/start")
    BaseResult startFlow(@Param("flowId") String flowId);


    /**
     * 签署流程撤销
     *
     * @param flowId  流程id
     * @param request 请求参数
     * @return
     */
    @RequestLine("PUT /v1/signflows/{flowId}/revoke")
    BaseResult revokeFlow(@Param("flowId") String flowId, FlowRevokeRequest request);


    /**
     * 签署流程归档
     *
     * @param flowId 流程id
     * @return
     */
    @RequestLine("PUT /v1/signflows/{flowId}/archive")
    BaseResult archiveFlow(@Param("flowId") String flowId);

}
