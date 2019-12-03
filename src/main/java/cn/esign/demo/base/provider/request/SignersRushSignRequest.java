package cn.esign.demo.base.provider.request;

import cn.esign.demo.base.constants.FlowNoticeType;
import lombok.Data;

/**
 * 流程签署人催签请求
 * @author zhexiu
 * @since 2019/7/21 下午4:54
 */
@Data
public class SignersRushSignRequest {

    /**
     * 催签人账号id
     */
    private String accountId;

    /**
     * 被催签人账号id
     */
    private String rushsignAccountId;

    /**
     * 通知方式，逗号分割，1-短信，2-邮件 3-支付宝 4-钉钉，默认按照走流程设置
     * @see FlowNoticeType
     */
    private String noticeTypes;
}
