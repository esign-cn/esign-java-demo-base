package cn.esign.demo.base.provider.response;

import lombok.Data;

/**
 * 通过上传方式创建文件响应
 * @author zhexiu
 * @since 2019/7/17 下午9:09
 */
@Data
public class FileGetUploadUrlResponse {

    /**
     * 文件Id
     */
    private String fileId;

    /**
     * 文件直传地址, 可以重复使用，但是只能传一样的文件，有效期一小时
     */
    private String uploadUrl;

}
