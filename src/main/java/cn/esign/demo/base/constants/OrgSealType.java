package cn.esign.demo.base.constants;

/**
 * 企业印章类型
 *
 * @author zhexiu
 * @since 2019/8/12 上午3:40
 */
public enum OrgSealType {

    TEMPLATE_ROUND("圆章"),

    TEMPLATE_OVAL("椭圆章"),;

    private String desc;

    OrgSealType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
