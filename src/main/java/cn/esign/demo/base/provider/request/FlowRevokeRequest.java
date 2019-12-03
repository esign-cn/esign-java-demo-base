package cn.esign.demo.base.provider.request;

import lombok.Data;

/**
 * 签署流程撤销请求
 * @author zhexiu
 * @since 2019/7/21 下午3:52
 */
@Data
public class FlowRevokeRequest {

    /**
     * 撤销原因
     */
    private String revokeReason;
}
