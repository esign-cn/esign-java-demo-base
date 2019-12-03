package cn.esign.demo.base.provider.response;

import cn.esign.demo.base.constants.PersonCertTypeEnum;
import lombok.Data;

/**
 * 个人信息
 * @author zhexiu
 * @since 2019/7/17 下午9:02
 */
@Data
public class PersonAccountResponse {

    /**
     * 个人账号id
     */
    private String accountId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件类型，详见个人证件类型说明 ，默认为身份证
     * @see PersonCertTypeEnum
     */
    private String idType;

    /**
     * 证件号
     */
    private String idNumber;

    /**
     * 联系方式，手机号码
     */
    private String mobile;

    /**
     * 联系方式，邮箱地址
     */
    private String email;

    /**
     * 第三方平台的用户id
     */
    private String thirdPartyUserId;

}
