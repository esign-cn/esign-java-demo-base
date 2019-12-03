package cn.esign.demo.base.provider.request;

import cn.esign.demo.base.constants.PersonSealType;
import cn.esign.demo.base.constants.SealColor;
import lombok.Data;

/**
 * 创建个人模版印章请求
 *
 * @author zhexiu
 * @since 2019/7/21 下午2:55
 */
@Data
public class CreatePersonTemplateSealRequest {

    /**
     * 印章别名
     */
    private String alias;

    /**
     * 印章颜色，RED-红色， BLUE-蓝色，BLACK-黑色
     *
     * @see SealColor
     */
    private String color;

    /**
     * 印章高度, 默认95px
     */
    private Integer height;

    /**
     * 印章宽度, 默认95px
     */
    private Integer width;

    /**
     * 模板类型, 详见个人印章样式说明 SQUARE, BORDERLESS, FZKC, HWLS, HWXK, HWXKBORDER, HYLSF, RECTANGLE, YGYJFCS, YGYMBXS, YYGXSF;
     *
     * @see PersonSealType
     */
    private String type;
}
