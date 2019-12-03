package cn.esign.demo.base.provider.request;

import lombok.Data;

import java.util.List;

/**
 * 添加手动盖章签署区请求
 * @author zhexiu
 * @since 2019/7/21 下午4:29
 */
@Data
public class SignfieldHandSignBatchAddRequest {

    /**
     * 签署区列表数据
     */
    private List<SignfieldHandSignBean> signfields;


    /**
     * 用于添加签署区
     */
    @Data
    public static class SignfieldHandSignBean   {

        /**
         * 签署人账号标识
         */
        private String signerAccountId;

        /**
         * 授权主体账号标识
         */
        private String authorizedAccountId;

        /**
         * 文件file id
         */
        private String fileId;


        /**
         * 印章id
         */
        private String sealId;

        /**
         * 签署类型，0-不限，1-单页签署，2-骑缝签署,4-关键字签署，默认1
         */
        private Integer signType ;

        /**
         * 签署区顺序，默认1,且不小于1，顺序越小越先处理
         */
        private int order;


        /**
         * 签约主体类别，0-个人，1-企业，默认0
         */
        private Integer actorIndentityType;

        /**
         * 流水号，调用方保证唯一性
         */
        private String thirdOrderNo;

        /**
         * 印章类型，支持多种类型时逗号分割，0-手绘印章，1-模版印章，为空不限制
         */
        private String sealType;


        /**
         * 是否指定位置，TRUE表示不允许更新位置，配置项，无默认值
         */
        private Boolean assignedPosbean;


        /**
         * 签署区位置信息
         */
        private SignfieldPosBean posBean;


    }
}
