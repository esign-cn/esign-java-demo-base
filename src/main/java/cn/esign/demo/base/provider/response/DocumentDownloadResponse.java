package cn.esign.demo.base.provider.response;

import lombok.Data;

import java.util.List;

/**
 * 流程文档下载信息
 * @author zhexiu
 * @since 2019/7/21 下午4:00
 */
@Data
public class DocumentDownloadResponse {

    /**
     * 文档列表数据
     */
    private List<DocumentBaseBean> docs;


    /**
     * 文档返回数据
     */
    @Data
    public static class DocumentBaseBean {

        /**
         * 文档id
         */
        private String fileId;

        /**
         * 文档名称,默认文件名称
         */
        private String fileName;

        /**
         * 文档地址
         */
        private String fileUrl;

    }

}
