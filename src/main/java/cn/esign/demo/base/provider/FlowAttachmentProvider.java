package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.AttachmentBatchAddRequest;
import cn.esign.demo.base.provider.response.AttachmentQueryResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 签署流程附件管理
 *
 * @author zhexiu
 * @since 2019/7/18 上午8:54
 */
public interface FlowAttachmentProvider {


    /**
     * 流程附件添加
     *
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/signflows/{flowId}/attachments ")
    BaseResult addAttachments(AttachmentBatchAddRequest request);


    /**
     * 流程附件列表
     *
     * @param flowId  流程id
     * @param fileIds 文档id列表,多个id使用“，”分隔
     * @return
     */
    @RequestLine("GET /v1/signflows/{flowId}/attachments?fileIds={fileIds}")
    BaseResult<AttachmentQueryResponse> queryAttachments(@Param("flowId") String flowId,
                                                         @Param("fileIds") String fileIds);


    /**
     * 流程附件删除
     *
     * @param flowId 流程id
     * @return
     */
    @RequestLine("DELETE /v1/signflows/{flowId}/attachments")
    BaseResult deleteAttachments(@Param("flowId") String flowId);


}
