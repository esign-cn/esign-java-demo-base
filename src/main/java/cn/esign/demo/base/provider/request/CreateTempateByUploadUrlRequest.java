package cn.esign.demo.base.provider.request;

import lombok.Data;

/**
 * 上传方式创建模板请求
 *
 * @author zhexiu
 * @since 2019/7/21 下午2:23
 */
@Data
public class CreateTempateByUploadUrlRequest {

  /** 模板文件md5值 */
  private String contentMd5;

  /** 目标文件的MIME类型 */
  private String contentType;

  /** 文件名称，必须带扩展名如:.pdf,.doc,.docx */
  private String fileName;

  /** 是否需要转成pdf，如果模板文件为.doc/.docx 传true，为pdf传false */
  private String convert2Pdf;
}
