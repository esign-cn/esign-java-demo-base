package cn.esign.demo.base.constants;

/**
 * 用户证件类型
 */
public enum PersonCertTypeEnum {
    CRED_PSN_CH_IDCARD("CRED_PSN_CH_IDCARD", "大陆身份证，默认值"),
    CRED_PSN_CH_TWCARD("CRED_PSN_CH_TWCARD", "台湾来往大陆通行证");

    private String certType;
    private String desc;

    PersonCertTypeEnum(String certType, String desc) {
        this.certType = certType;
        this.desc = desc;
    }

    public static PersonCertTypeEnum from(String certType) {
        for (PersonCertTypeEnum userCenterCertTypeEnum : values()) {
            if (userCenterCertTypeEnum.getCertType().equals(certType)) {
                return userCenterCertTypeEnum;
            }
        }

        return null;
    }

    public String getCertType() {
        return this.certType;
    }

    public String getDesc() {
        return this.desc;
    }
}
