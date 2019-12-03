package cn.esign.demo.base.provider.request;

import lombok.Data;

import java.util.List;

/**
 * 添加签署方自动盖章签署区请求
 * @author zhexiu
 * @since 2019/7/21 下午4:28
 */
@Data
public class SignfieldAutoSignBatchAddRequest {

    /**
     * 签署区列表数据
     */
    private List<SignfieldAutoSignBean> signfields;


    /**
     * 用于添加签署区
     */
    @Data
    public static class SignfieldAutoSignBean {

        /**
         * 授权主体账号标识
         */
        private String authorizedAccountId;

        /**
         * 文件file id
         */
        private String fileId;

        /**
         * 签署类型， 1-单页签署，2-骑缝签署,4-关键字签署，默认1
         */
        private Integer signType;

        /**
         * 签署区顺序，默认1,且不小于1，顺序越小越先处理
         */
        private int order;

        /**
         * 流水号，调用方保证唯一性
         */
        private String thirdOrderNo;

        /**
         * 印章id不能为空
         */
        private String sealId;


        /**
         * 签署区位置信息
         */
        private SignfieldPosBean posBean;

    }

}
