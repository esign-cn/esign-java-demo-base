package cn.esign.demo.base.constants;

/**
 * 印章颜色
 *
 * @author zhexiu
 * @since 2019/8/12 上午3:32
 */
public enum SealColor {

    RED("红色"),
    BLUE("蓝色"),
    BLACK("黑色"),;

    private String desc;


    SealColor(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
