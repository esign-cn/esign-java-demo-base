package cn.esign.demo.base.provider.response;

import cn.esign.demo.base.constants.OrgCertTypeEnum;
import lombok.Data;

/**
 * 机构账号信息
 * @author zhexiu
 * @since 2019/7/17 下午9:02
 */
@Data
public class OrganizationAccountResponse {

    /**
     * 机构账号Id
     */
    private String orgId;

    /**
     * 机构唯一标识，可传入第三方平台机构id、企业证件号、企业邮箱等如果设置则作为账号唯一性字段，相同id不可重复创建。（个人用户与机构的唯一标识不可重复）
     */
    private String thirdPartyUserId;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 证件类型，详见机构证件类型说明 ，默认CRED_ORG_USCC
     * @see  OrgCertTypeEnum
     */
    private String idType;

    /**
     * 证件号，默认为空
     */
    private String idNumber;

}
