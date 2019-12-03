package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.CreateTempateByUploadUrlRequest;
import cn.esign.demo.base.provider.request.TemplateStructCompCreateRequest;
import cn.esign.demo.base.provider.response.CreateTempateByUploadUrlResponse;
import cn.esign.demo.base.provider.response.TemplateInfoReponse;
import feign.Param;
import feign.RequestLine;

import java.util.List;

/**
 * 模板管理
 *
 * @author zhexiu
 * @since 2019/7/17 下午9:06
 */
public interface TemplateProvider {

    /**
     * 通过上传方式创建模板
     *
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/docTemplates/createByUploadUrl")
    BaseResult<CreateTempateByUploadUrlResponse> createByUploadUrl(CreateTempateByUploadUrlRequest request);

    /**
     * 模板添加输入项组件
     *
     * @param templateId 模版ID
     * @param request    请求参数
     * @return 组件KEY列表
     */
    @RequestLine("POST /v1/docTemplates/{templateId}/components")
    BaseResult<List<String>> addStructComponent(@Param("templateId") String templateId, TemplateStructCompCreateRequest request);


    /**
     * 删除模板输入项组件
     *
     * @param templateId 模版ID
     * @param ids        输入项组件id集合，逗号分隔
     * @return
     */
    @RequestLine("DELETE /v1/docTemplates/{templateId}/components/{ids}")
    BaseResult deleteStructComponent(@Param("templateId") String templateId, @Param("ids") String ids);


    /**
     * 查询模板详情
     *
     * @param templateId 模版ID
     * @return
     */
    @RequestLine("GET /v1/docTemplates/{templateId}")
    BaseResult<TemplateInfoReponse> getInfo(@Param("templateId") String templateId);
}
