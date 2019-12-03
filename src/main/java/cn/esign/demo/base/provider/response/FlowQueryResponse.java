package cn.esign.demo.base.provider.response;

import cn.esign.demo.base.provider.request.FlowCreateRequest;
import lombok.Data;

/**
 * 签署流程详情
 * @author zhexiu
 * @since 2019/7/21 下午3:44
 */
@Data
public class FlowQueryResponse {

    /**
     * 流程Id
     */
    private String flowId;

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 是否自动归档
     */
    private Boolean autoArchive;


    /**
     * 流程状态,0-草稿 1-签署中 2-完成 3-撤销 4-终止 5-过期 6-删除 7-拒签
     *
     */
    private int flowStatus;

    /**
     * 流程描述
     */
    private String flowDesc;


    /**
     * 业务场景
     */
    private String businessScene;


    /**
     * 流程开始时间
     */
    private Long flowStartTime;

    /**
     * 流程结束时间
     */
    private Long flowEndTime;

    /**
     * 任务配置信息
     */
    private FlowCreateRequest.FlowConfigBean configInfo;


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
     * 合同开始生效时间,毫秒
     */
    private Long contractEffective;


}
