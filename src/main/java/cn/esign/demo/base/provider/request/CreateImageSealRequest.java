package cn.esign.demo.base.provider.request;

import cn.esign.demo.base.constants.SealImageCreateType;
import lombok.Data;

/**
 * 创建图片印章请求
 *
 * @author zhexiu
 * @since 2019/7/21 下午2:55
 */
@Data
public class CreateImageSealRequest {

    /**
     * 印章别名
     */
    private String alias;

    /**
     * 印章高度, 个人默认95px, 机构默认159px
     */
    private Integer height;

    /**
     * 印章宽度, 个人默认95px, 机构默认159px
     */
    private Integer width;

    /**
     * 印章数据类型，BASE64-base64格式，FILEKEY-文件filekey
     *
     * @see SealImageCreateType
     */
    private String type;

    /**
     * 印章数据，base64格式或是fileKey
     */
    private String data;

    /**
     * 是否对图片进行透明化处理，如图片有背景颜色，直接使用会遮挡文字，建议进行透明化处理，默认false，不做任何处理
     */
    private Boolean transparentFlag;
}
