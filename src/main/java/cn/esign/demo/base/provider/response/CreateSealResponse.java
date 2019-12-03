package cn.esign.demo.base.provider.response;

import lombok.Data;

/**
 * 印章信息
 * @author zhexiu
 * @since 2019/7/21 下午2:55
 */
@Data
public class CreateSealResponse {

    /**
     * 印章id
     */
    private String sealId;

    /**
     * 印章fileKey
     */
    private String fileKey;

    /**
     * 印章下载地址
     */
    private String url;

    /**
     * 印章高度
     */
    private Integer height;

    /**
     * 印章宽度
     */
    private Integer width;

}
