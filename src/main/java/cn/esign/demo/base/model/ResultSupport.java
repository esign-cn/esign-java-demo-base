package cn.esign.demo.base.model;

/**
 * @author zhexiu
 * @since 2019/7/16 下午5:17
 */
public class ResultSupport {

    public static final int SUCCESS = 0;

    public static boolean isSucess(BaseResult result) {
        return result.getCode() == SUCCESS;
    }

    public static boolean isFail(BaseResult result) {
        return result.getCode() != SUCCESS;
    }
}
