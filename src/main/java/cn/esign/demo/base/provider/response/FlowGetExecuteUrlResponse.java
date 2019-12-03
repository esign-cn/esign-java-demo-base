package cn.esign.demo.base.provider.response;

import lombok.Data;

/**
 * 签署地址信息
 * @author zhexiu
 * @since 2019/7/21 下午4:53
 */
@Data
public class FlowGetExecuteUrlResponse {

    /**
     * 长链地址
     */
    private String url;

    /**
     * 短链地址
     */
    private String shortUrl;

}
