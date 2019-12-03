package cn.esign.demo.base.provider.request;

import cn.esign.demo.base.constants.PersonCertTypeEnum;
import lombok.Data;

/**
 * 更新个人账号信息请求
 * @author zhexiu
 * @since 2019/7/17 下午9:01
 */
@Data
public class PersonAccountUpdateRequest {


    /**
     * 姓名，默认不变
     */
    private String name;

    /**
     * 证件类型，详见个人证件类型说明 ，默认为身份证
     * @see  PersonCertTypeEnum
     */
    private String idType;


    /**
     * 证件号
     */
    private String idNumber;

    /**
     * 联系方式，手机号码，默认不变
     */
    private String mobile;


    /**
     * 联系方式，邮箱地址，默认不变
     */
    private String email;

}
