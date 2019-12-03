package cn.esign.demo.base.constants;

/**
 * 企业印章中心图案类型
 *
 * @author zhexiu
 * @since 2019/8/12 上午3:40
 */
public enum OrgSealCentral {


    NONE("圆形无五角星"),

    STAR("圆形有五角星"),;

    private String desc;

    OrgSealCentral(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
