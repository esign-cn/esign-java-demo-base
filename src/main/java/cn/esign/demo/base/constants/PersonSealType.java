package cn.esign.demo.base.constants;

/**
 * 个人印章样式
 *
 * @author zhexiu
 * @since 2019/8/12 上午3:34
 */
public enum PersonSealType {

    SQUARE("仿宋方形印章"),
    BORDERLESS("无框矩形印章"),
    FZKC("方正狂草"),
    HWLS("华文隶书"),
    HWXK("华文行楷"),
    HWXKBORDER("有框华文行楷"),
    HYLSF("汉仪篆书繁体"),
    RECTANGLE("矩形印章"),
    YGYJFCS("叶根友疾风草书"),
    YGYMBXS("叶根友毛笔行书"),
    YYGXSF("钟齐蔡云汉毛笔行书"),;

    private String desc;


    PersonSealType(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
