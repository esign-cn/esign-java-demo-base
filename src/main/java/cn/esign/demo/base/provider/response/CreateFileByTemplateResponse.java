package cn.esign.demo.base.provider.response;

import lombok.Data;

/**
 * 模板创建文件响应
 * @author zhexiu
 * @since 2019/7/17 下午9:07
 */
@Data
public class CreateFileByTemplateResponse {

    /**
     * 文件id
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件下载地址，有效期一小时
     */
    private String downloadUrl;


}
