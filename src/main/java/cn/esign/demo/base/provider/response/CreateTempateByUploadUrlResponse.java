package cn.esign.demo.base.provider.response;

import lombok.Data;

/**
 * 上传方式创建模板响应
 * @author zhexiu
 * @since 2019/7/21 下午2:25
 */
@Data
public class CreateTempateByUploadUrlResponse {

    /**
     * 模板id
     */
    private String templateId;

    /**
     * 文件直传地址，需要用此上传地址使用put方式上传文件，只有文件上传后模板才可用
     */
    private String uploadUrl;

}
