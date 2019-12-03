package cn.esign.demo.base.provider.response;

import lombok.Data;

import java.util.List;

/**
 * 添加签署区任务响应
 * @author zhexiu
 * @since 2019/7/21 下午4:26
 */
@Data
public class SignfieldAddResponse {

    /**
     * 签署区列表数据
     */
    private List<SignfieldAddRespBean> signfieldBeans;


    /**
     * 签署区信息
     */
    @Data
    public static class SignfieldAddRespBean {


        /**
         * 签署区id
         */
        private String signfieldId;

        /**
         * 文档ID
         */
        private String fileId;

        /**
         * 用户ID
         */
        private String accountId;

    }

}
