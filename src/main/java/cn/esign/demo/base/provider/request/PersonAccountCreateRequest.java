package cn.esign.demo.base.provider.request;

import cn.esign.demo.base.constants.PersonCertTypeEnum;
import lombok.Data;

/**
 * 个人账号创建请求
 * @author zhexiu
 * @since 2019/7/17 下午9:01
 */
@Data
public class PersonAccountCreateRequest {

    /**
     * 用户唯一标识，可传入第三方平台的个人用户id、证件号、手机号、邮箱等，如果设置则作为账号唯一性字段，相同信息不可重复创建。
     * （个人用户与机构的唯一标识不可重复）
     */
    private String thirdPartyUserId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 证件类型,详见个人证件类型说明 ，默认CRED_PSN_CH_IDCARD
     * @see PersonCertTypeEnum
     */
    private String idType;

    /**
     * 证件号，默认空
     */
    private String idNumber;

    /**
     * 手机号码，默认空
     */
    private String mobile;

    /**
     * 邮箱地址，默认空
     */
    private String email;

}
