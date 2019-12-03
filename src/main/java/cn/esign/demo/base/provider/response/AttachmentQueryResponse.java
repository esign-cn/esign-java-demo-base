package cn.esign.demo.base.provider.response;

import lombok.Data;

import java.util.List;

/**
 * 流程附件列表信息
 * @author zhexiu
 * @since 2019/7/21 下午4:04
 */
@Data
public class AttachmentQueryResponse {


    /**
     * 附件列表数据
     */
    private List<AttachmentResBean> attachments;


    /**
     * 附件返回数据
     */
    @Data
    public static class AttachmentResBean  {

        /**
         * 附件Id
         */
        private String fileId;

        /**
         * 附件名称
         */
        private String attachmentName;

        /**
         * 附件地址
         */
        private String attachmentUrl;

        /**
         * 创建时间
         */
        private Long createTime;
    }
}
