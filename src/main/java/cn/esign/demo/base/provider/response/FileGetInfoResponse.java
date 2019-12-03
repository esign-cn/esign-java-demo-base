package cn.esign.demo.base.provider.response;

import lombok.Data;

/**
 * 获取文件下载地址
 * @author zhexiu
 * @since 2019/7/17 下午9:09
 */
@Data
public class FileGetInfoResponse {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件下载地址,有效期一小时
     */
    private String downloadUrl;

}
