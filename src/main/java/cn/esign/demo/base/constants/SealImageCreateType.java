package cn.esign.demo.base.constants;

/**
 * 图片印章创建类型
 *
 * @author zhexiu
 * @since 2019/8/12 上午3:53
 */
public enum SealImageCreateType {

    BASE64("base64格式"),
    FILEKEY("文件filekey"),;

    private String desc;

    SealImageCreateType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
