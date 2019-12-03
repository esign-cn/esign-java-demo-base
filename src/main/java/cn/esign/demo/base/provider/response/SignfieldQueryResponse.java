package cn.esign.demo.base.provider.response;

import cn.esign.demo.base.provider.request.SignfieldPosBean;
import lombok.Data;

import java.util.List;

/**
 * 流程签署区信息
 * @author zhexiu
 * @since 2019/7/21 下午4:13
 */
@Data
public class SignfieldQueryResponse {

    /**
     * 签署区列表数据
     */
    private List<SignfieldBean> signfields;

    /**
     * 签署区查询数据
     */
    @Data
    public class SignfieldBean   {


        /**
         * 签约主体类别，0-个人，1-企业，默认0
         */
        private Integer actorIndentityType ;

        /**
         * 是否添加签章时间戳，TRUE表示添加，配置项，默认不添加
         */
        private Boolean addSignTime = Boolean.FALSE;

        /**
         * 签署区id
         */
        private String signfieldId;


        /**
         * 流程id
         */
        private String flowId;

        /**
         * 签署人账号标识
         */
        private String signerAccountId;

        /**
         * 授权主体账号标识
         */
        private String authorizedAccountId;

        /**
         * 文件fileId
         */
        private String fileId;


        /**
         * 签署区状态
         */
        private Integer status;


        /**
         * 状态描述
         */
        private String statusDescription;

        /**
         * 执行失败原因
         */
        private String executeFailedReason;

        /**
         * 添加时间
         */
        private Long addTime;


        /**
         * 印章类型，支持多种类型时逗号分割，0-手绘印章，1-模版印章，为空不限制
         */
        private String sealType;

        /**
         * 签署类型，0-不限，1-单页签署，2-骑缝签署,4-关键字签署，默认1
         */
        private Integer signType ;

        /**
         * 签署区顺序，默认1,且不小于1，顺序越小越先处理
         */
        private Integer order;

        /**
         * 是否自动执行，TRUE需要静默授权，配置项，无默认值
         */
        private Boolean autoExecute;

        /**
         * 流水号，调用方保证唯一性
         */
        private String thirdOrderNo;

        /**
         * 是否指定位置，TRUE表示不允许更新位置，配置项，无默认值
         */
        private Boolean assignedPosbean;

        /**
         * 是否指定印章数据，TRUE表示不允许更新印章，配置项，无默认值
         */
        private Boolean assignedSeal;


        /**
         * 印章id
         */
        private String sealId;

        /**
         * 印章文件file key
         */
        private String sealFileKey;

        /**
         * 签署区位置信息
         */
        private SignfieldPosBean posBean;


    }


}
