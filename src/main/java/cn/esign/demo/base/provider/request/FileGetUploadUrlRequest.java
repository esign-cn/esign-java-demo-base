package cn.esign.demo.base.provider.request;

import lombok.Data;

/**
 * 通过上传方式创建文件请求
 *
 * @author zhexiu
 * @since 2019/7/17 下午9:07
 */
@Data
public class FileGetUploadUrlRequest {

    /** 所属账号id，即个人账号id或机构账号id，如不传，则默认属于对接平台 */
    private String accountId;

    /** 先计算文件md5值，在对该md5值进行base64编码 */
    private String contentMd5;

    /** 目标文件的MIME类型 */
    private String contentType;

    /** 文件名称（必须带上文件扩展名，不然会导致后续发起流程校验过不去 示例：合同.pdf ） */
    private String fileName;

    /** 文件大小，单位bit */
    private Long fileSize;
}
