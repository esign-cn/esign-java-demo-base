package cn.esign.demo.base.provider.request;

import lombok.Data;

import java.util.List;

/**
 * 流程附件添加请求
 *
 * @author zhexiu
 * @since 2019/7/21 下午4:08
 */
@Data
public class AttachmentBatchAddRequest {

    /**
     * 附件列表数据
     */
    private List<AttachmentBean> attachments;


    /**
     * 附件数据
     */
    @Data
    public static class AttachmentBean {

        /**
         * 附件Id
         */
        private String fileId;

        /**
         * 附件名称
         */
        private String attachmentName;

    }
}
