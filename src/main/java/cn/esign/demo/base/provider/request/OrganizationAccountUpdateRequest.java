package cn.esign.demo.base.provider.request;

import cn.esign.demo.base.constants.OrgCertTypeEnum;
import lombok.Data;

/**
 * 修改机构信息请求
 * @author zhexiu
 * @since 2019/7/17 下午9:01
 */
@Data
public class OrganizationAccountUpdateRequest {


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
