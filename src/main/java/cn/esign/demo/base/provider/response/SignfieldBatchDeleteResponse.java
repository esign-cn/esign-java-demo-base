package cn.esign.demo.base.provider.response;

import lombok.Data;

import java.util.List;

/**
 * @author zhexiu
 * @since 2019/7/21 下午4:33
 */
@Data
public class SignfieldBatchDeleteResponse {

    private List<SignfieldOptResult> deleteResults;


    @Data
    public static class SignfieldOptResult {

        /**
         * 签署区id
         */
        private String signfieldId;

        /**
         * 操作结果，0-成功，1-失败
         */
        private Integer optResult;

        /**
         * 失败原因
         */
        private String failedReason;

        /**
         * 签署成功后返回签署记录Id
         */
        private String signlogId;
    }
}
