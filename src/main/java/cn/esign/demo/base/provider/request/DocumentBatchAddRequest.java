package cn.esign.demo.base.provider.request;

import lombok.Data;

import java.util.List;

/**
 * 流程文档添加请求
 *
 * @author zhexiu
 * @since 2019/7/21 下午3:56
 */
@Data
public class DocumentBatchAddRequest {

    /** 文档列表数据 */
    private List<DocumentBean> docs;

    /** 文档数据 */
    @Data
    public static class DocumentBean {

        /** 文档id */
        private String fileId;

        /** 文档名称,默认文件名称 */
        private String fileName;

        /** 文档密码 */
        private String filePassword;

        /** 是否加密，0-不加密，1-加，默认0 */
        private int encryption = 0;
    }
}
