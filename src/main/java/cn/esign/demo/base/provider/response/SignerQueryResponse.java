package cn.esign.demo.base.provider.response;

import lombok.Data;

import java.util.List;

/**
 * 签署人列表信息
 * @author zhexiu
 * @since 2019/7/21 下午4:55
 */
@Data
public class SignerQueryResponse {

    /**
     * 签署人列表
     */
    private List<SignerBean> signers;


    /**
     * 签署人查询数据
     */
    @Data
    public static class SignerBean {

        /**
         * 签署状态, 0-待签, 1-未签, 2-已签 3-待审批 4-拒签
         */
        private int signStatus;

        /**
         * 签署顺序
         */
        private int signOrder = 1;

        /**
         *   签署人账号id
         */
        private String signerAccountId;

        /**
         * 签署人名称
         */
        private String signerName;

        /**
         * 签署人是否已实名
         */
        private boolean signerRealName;

        /**
         * 签约主体账号id
         */
        private String signerAuthorizedAccountId;

        /**
         * 签署主体名称
         */
        private String signerAuthorizedAccountName;

        /**
         * 签署主体是否已实名
         */
        private boolean signerAuthorizedAccountRealName;

        /**
         * 签署主体类型, 0-个人, 1-企业
         */
        private Integer signerAuthorizedAccountType;

        /**
         * 第三方流水号
         */
        private String thirdOrderNo;

    }

}
