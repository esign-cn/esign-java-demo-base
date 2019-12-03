package cn.esign.demo.base.constants;

/**
 * 流程通知类型
 *
 * @author zhexiu
 * @since 2019/8/12 上午4:12
 */
public enum FlowNoticeType {

    MOBILE(1, "短信"),
    EMIAL(2, "邮件"),
    ALIPAY(3, "支付宝"),
    DINGDING(4, "钉钉"),;

    private Integer code;
    private String desc;

    FlowNoticeType(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
