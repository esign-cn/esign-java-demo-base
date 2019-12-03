package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.DocumentBatchAddRequest;
import cn.esign.demo.base.provider.response.DocumentDownloadResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 签署流程文档管理
 *
 * @author zhexiu
 * @since 2019/7/18 上午8:54
 */
public interface FlowDocProvider {


    /**
     * 流程文档添加
     *
     * @param flowId  流程id
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/signflows/{flowId}/documents")
    BaseResult addDocuments(@Param("flowId") String flowId, DocumentBatchAddRequest request);


    /**
     * 流程文档删除
     *
     * @param flowId  流程id
     * @param fileIds 文档id列表,多个id使用“，”分隔
     * @return
     */
    @RequestLine("DELETE /v1/signflows/{flowId}/documents?fileIds={fileIds}")
    BaseResult deleteDocuments(@Param("flowId") String flowId,
                               @Param("fileIds") String fileIds);


    /**
     * 流程文档下载
     *
     * @param flowId 流程id
     * @return
     */
    @RequestLine("GET /v1/signflows/{flowId}/documents")
    BaseResult<DocumentDownloadResponse> getFlowFinishDocuments(@Param("flowId") String flowId);

}
