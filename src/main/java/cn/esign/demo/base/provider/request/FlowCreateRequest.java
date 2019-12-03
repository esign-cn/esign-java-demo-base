package cn.esign.demo.base.provider.request;

import cn.esign.demo.base.constants.FlowNoticeType;
import lombok.Data;

/**
 * 签署流程创建请求
 * @author zhexiu
 * @since 2019/7/21 下午3:37
 */
@Data
public class FlowCreateRequest {

    /**
     * 自动归档不能为空
     */
    private Boolean autoArchive;

    /**
     * 业务场景
     */
    private String businessScene;

    /**
     * 任务配置信息
     */
    private FlowConfigBean configInfo;

    /**
     * 发起方账户id
     */
    private String initiatorAccountId;


    /**
     * 发起方主体id
     */
    private String initiatorAuthorizedAccountId;

    /**
     * 签署有效截止时间,毫秒
     */
    private Long signValidity;


    /**
     * 合同有效截止时间,毫秒
     */
    private Long contractValidity;

    /**
     * 合同提前多少小时提醒，小时,范围，1-360
     */
    private Integer contractRemind;


    /**
     * 流程配置
     */
    @Data
    public static class FlowConfigBean   {


        /**
         * 签署平台，逗号分割，1-开放服务h5，2-支付宝小程序，3-微信小程序。 默认1"
         */
        private String signPlatform;

        /**
         * 通知方式，逗号分割，1-短信，2-邮件 3-支付宝 4-钉钉。 默认1
         * @see FlowNoticeType
         */
        private String noticeType;

        /**
         * 通知开发者地址
         */
        private String noticeDeveloperUrl;

        /**
         * 签署完成重定向地址
         */
        private String redirectUrl;

    }


}
