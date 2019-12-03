package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.SignersRushSignRequest;
import cn.esign.demo.base.provider.response.FlowGetExecuteUrlResponse;
import cn.esign.demo.base.provider.response.SignerQueryResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 签署流程签署人管理
 *
 * @author zhexiu
 * @since 2019/7/18 上午8:54
 */
public interface FlowSignerProvider {


    /**
     * 流程签署人列表
     *
     * @param flowId 流程id
     * @return
     */
    @RequestLine("GET /v1/signflows/{flowId}/signers")
    BaseResult<SignerQueryResponse> querySigners(@Param("flowId") String flowId);


    /**
     * 流程签署人催签
     *
     * @param flowId  流程id
     * @param request 请求参数
     * @return
     */
    @RequestLine("PUT /v1/signflows/{flowId}/signers/rushsign")
    BaseResult rushsign(@Param("flowId") String flowId,
                        SignersRushSignRequest request);


    /**
     * 获取签署地址
     *
     * @param flowId     流程id
     * @param accountId  签署人账号id, 可指定, 默认用当前登录人账号id
     * @param organizeId 机构Id，传入本参数后，获取当前操作人代签企业的签署任务,默认空
     * @param urlType    链接类型: 0-签署链接;1-预览链接;默认0
     * @return
     */
    @RequestLine("GET /v1/signflows/{flowId}/executeUrl?accountId={accountId}&organizeId={organizeId}&urlType={urlType}")
    BaseResult<FlowGetExecuteUrlResponse> getFlowExecuteUrl(@Param("flowId") String flowId,
                                                            @Param("accountId") String accountId,
                                                            @Param("organizeId") String organizeId,
                                                            @Param("urlType") String urlType);


}
