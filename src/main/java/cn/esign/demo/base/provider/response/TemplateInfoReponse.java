package cn.esign.demo.base.provider.response;

import cn.esign.demo.base.provider.request.TemplateStructCompCreateRequest;
import lombok.Data;

import java.util.List;

/**
 * 模版详情
 * @author zhexiu
 * @since 2019/7/21 下午2:40
 */
@Data
public class TemplateInfoReponse {

    /**
     * 模板id
     */
    private String templateId;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 模板文件下载地址
     */
    private String downloadUrl;
    /**
     * 文件大小
     */
    private Long fileSize;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 模板组件信息
     */
    private List<TemplateStructCompCreateRequest.StructComponent> structComponents;


}
