package cn.esign.demo.base.provider.response;

import lombok.Data;

/**
 * 印章信息
 * @author zhexiu
 * @since 2019/8/12 上午4:01
 */
@Data
public class OrgSealBean {

    /**
     * 印章别名
     */
    private String alias;

    /**
     * 印章创建时间(毫秒)
     */
    private Long createDate;

    /**
     * 印章id
     */
    private String sealId;

    /**
     * 印章fileKey
     */
    private String fileKey;

    /**
     * 印章下载地址
     */
    private String url;

    /**
     * 印章高度
     */
    private Integer height;

    /**
     * 印章宽度
     */
    private Integer width;


    /**
     * 默认印章标识
     */
    private Boolean defaultFlag;

    /**
     * 印章类型，1-机构模板章，2-个人模板章，3-自定义印章，4-手绘章
     */
    private Integer sealType;

    /**
     * 印章业务类型，CANCELLATION-作废章，COMMON-其它
     */
    private String sealBizType;

}
