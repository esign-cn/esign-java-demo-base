package cn.esign.demo.base.provider.request;

import lombok.Data;

/**
 * 签署区位置信息
 * @author zhexiu
 * @since 2019/7/21 下午4:22
 */
@Data
public class SignfieldPosBean {

    /**
     * 页码信息，可以','或'-'分割
     */
    private String posPage;

    /**
     * x坐标
     */
    private Float posX;

    /**
     * y坐标
     */
    private Float posY;

    /**
     * 签署区宽
     */
    private Float width;


    /**
     * 是否添加签署时间戳
     */
    private Boolean addSignTime;
}
