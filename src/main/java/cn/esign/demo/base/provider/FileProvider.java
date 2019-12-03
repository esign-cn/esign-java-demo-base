package cn.esign.demo.base.provider;

import cn.esign.demo.base.model.BaseResult;
import cn.esign.demo.base.provider.request.CreateFileByTemplateRequest;
import cn.esign.demo.base.provider.request.FileGetUploadUrlRequest;
import cn.esign.demo.base.provider.response.CreateFileByTemplateResponse;
import cn.esign.demo.base.provider.response.FileGetInfoResponse;
import cn.esign.demo.base.provider.response.FileGetUploadUrlResponse;
import feign.Param;
import feign.RequestLine;

/**
 * 文件上传管理
 *
 * @author zhexiu
 * @since 2019/7/17 下午9:06
 */
public interface FileProvider {

    /**
     * 获取上传文件地址
     *
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/files/getUploadUrl")
    BaseResult<FileGetUploadUrlResponse> getUploadUrl(FileGetUploadUrlRequest request);

    /**
     * 通过模板创建文件
     *
     * @param request 请求参数
     * @return
     */
    @RequestLine("POST /v1/files/createByTemplate")
    BaseResult<CreateFileByTemplateResponse> createByTemplate(CreateFileByTemplateRequest request);


    /**
     * 获取文件下载地址
     *
     * @param fileId 文件ID
     * @return
     */
    @RequestLine("GET /v1/files/{fileId}")
    BaseResult<FileGetInfoResponse> getFileInfo(@Param("fileId") String fileId);
}
