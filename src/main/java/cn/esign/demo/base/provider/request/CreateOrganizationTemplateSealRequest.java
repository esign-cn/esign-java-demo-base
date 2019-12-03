package cn.esign.demo.base.provider.request;

import cn.esign.demo.base.constants.OrgSealCentral;
import cn.esign.demo.base.constants.OrgSealType;
import cn.esign.demo.base.constants.SealColor;
import lombok.Data;

/**
 * 创建机构模版印章请求
 *
 * @author zhexiu
 * @since 2019/7/21 下午2:55
 */
@Data
public class CreateOrganizationTemplateSealRequest {

    /**
     * 印章别名
     */
    private String alias;

    /**
     * 印章颜色，RED-红色，BLUE-蓝色，BLACK-黑色
     *
     * @see SealColor
     */
    private String color;

    /**
     * 印章高度，默认159px
     */
    private Integer height;

    /**
     * 印章宽度，默认159px
     */
    private Integer width;

    /**
     * 横向文
     */
    private String htext;

    /**
     * 下弦文
     */
    private String qtext;

    /**
     * 模板类型，TEMPLATE_ROUND-圆章，TEMPLATE_OVAL-椭圆章， 详见机构印章样式说明
     *
     * @see OrgSealType
     */
    private String type;

    /**
     * 中心图案类型，STAR-圆形有五角星，NONE-圆形无五角星， 详见机构印章样式说明
     *
     * @see OrgSealCentral
     */
    private String central;
}
