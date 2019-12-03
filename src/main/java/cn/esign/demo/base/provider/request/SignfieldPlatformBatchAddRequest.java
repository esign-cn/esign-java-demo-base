package cn.esign.demo.base.provider.request;

import lombok.Data;

import java.util.List;

/**
 * 添加平台自动盖章签署区请求
 * @author zhexiu
 * @since 2019/7/21 下午4:20
 */
@Data
public class SignfieldPlatformBatchAddRequest {

    /**
     * 签署区列表
     */
    private List<SignfieldPlatformBean> signfields;

    /**
     * 签署区数据-固定属性
     */
    @Data
    public static class SignfieldPlatformBean {


        /**
         * 文件file id
         */
        private String fileId;

        /**
         * 签署类型， 1-单页签署，2-骑缝签署,4-关键字签署，默认1"
         */
        private Integer signType;

        /**
         * 签署区顺序，默认1,且不小于1，顺序越小越先处理
         */
        private int order;

        /**
         * 印章id
         */
        private String sealId;

        /**
         * 流水号，调用方保证唯一性
         */
        private String thirdOrderNo;

        /**
         * 签署区位置信息
         */
        private SignfieldPosBean posBean;

    }


}
