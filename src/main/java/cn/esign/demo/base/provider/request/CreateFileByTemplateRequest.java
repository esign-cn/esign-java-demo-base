package cn.esign.demo.base.provider.request;

import lombok.Data;

import java.util.Map;

/**
 * 模板创建文件请求
 *
 * @author zhexiu
 * @since 2019/7/17 下午9:07
 */
@Data
public class CreateFileByTemplateRequest {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 模板编号, 可通过e签宝网站->企业模板下创建和查询
     */
    private String templateId;

    /**
     * 输入项填充内容，key:value 传入；可使用输入项组件id+填充内容，也可使用输入项组件key+填充内容方式填充
     */
    private Map<String, String> simpleFormFields;

}
