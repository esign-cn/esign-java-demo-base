package cn.esign.demo.base.facade;

import cn.esign.demo.base.http.OkHttp3Client;
import cn.esign.demo.base.http.OkHttpClient;
import cn.esign.demo.base.provider.FileProvider;
import cn.esign.demo.base.provider.request.FileGetUploadUrlRequest;
import cn.esign.demo.base.provider.response.FileGetUploadUrlResponse;
import cn.esign.demo.base.utils.FileUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Base64.Encoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileFacade.class);

    public FileFacade() {
    }

    public String uploadFile(byte[] body, String fileName) {
        byte[] fileMd5 = FileUtils.getBytesMD5(body);
        Encoder encoder = Base64.getEncoder();
        String encodedText = encoder.encodeToString(fileMd5);
        FileGetUploadUrlRequest request = new FileGetUploadUrlRequest();
        request.setFileName(fileName);
        request.setContentType("application/octet-stream");
        request.setContentMd5(encodedText);
        request.setFileSize((long) body.length);
        request.setAccountId(null);
        FileProvider fileProvider = OkHttp3Client.getApi(FileProvider.class);
        FileGetUploadUrlResponse response = fileProvider.getUploadUrl(request).getData();
        String uploadUrl = response.getUploadUrl();
        Map<String, String> headers = new HashMap(2);
        headers.put("Content-MD5", encodedText);
        headers.put("Content-Type", "application/octet-stream");
        this.uploadFile(body, uploadUrl, headers);
        String fileId = response.getFileId();
        return fileId;
    }

    public String uploadFile(String path) {
        File file = new File(path);
        return this.uploadFile(file);
    }

    public String uploadFile(File file) {
        byte[] fileMd5 = FileUtils.getFileMD5(file);
        Encoder encoder = Base64.getEncoder();
        String encodedText = encoder.encodeToString(fileMd5);
        FileGetUploadUrlRequest request = new FileGetUploadUrlRequest();
        request.setFileName(file.getName());
        request.setContentType("application/octet-stream");
        request.setContentMd5(encodedText);
        request.setFileSize(file.length());
        request.setAccountId(null);
        FileProvider fileProvider = OkHttp3Client.getApi(FileProvider.class);
        FileGetUploadUrlResponse response = fileProvider.getUploadUrl(request).getData();
        String uploadUrl = response.getUploadUrl();
        Map<String, String> headers = new HashMap(2);
        headers.put("Content-MD5", encodedText);
        headers.put("Content-Type", "application/octet-stream");
        this.uploadFile(file, uploadUrl, headers);
        String fileId = response.getFileId();
        return fileId;
    }

    private File downloadFile(String downloadUrl, String fileName) {
        String savePath = System.getProperty("java.io.tmpdir");
        LOGGER.info("savePath=" + savePath);

        try {
            long startTime = System.currentTimeMillis();
            File file = FileUtils.downLoadFromUrl(downloadUrl, fileName, savePath);
            long endTime = System.currentTimeMillis();
            LOGGER.info("下载pdf花费时间：{} ms", endTime - startTime);
            return file;
        } catch (Exception var9) {
            var9.printStackTrace();
            throw new RuntimeException("文件下载错误");
        }
    }

    private void uploadFile(File file, String uploadUrl, Map<String, String> headers) {
        try {
            long startTime = System.currentTimeMillis();
            String result = OkHttpClient.upload(uploadUrl, file, headers);
            this.uploadFileHandler(result);
            long endTime = System.currentTimeMillis();
            LOGGER.info(" 上传pdf花费时间：{} ms", endTime - startTime);
        } catch (Exception var9) {
            LOGGER.error("上传出错");
            throw new RuntimeException("文件上传错误");
        }
    }

    private void uploadFile(byte[] content, String uploadUrl, Map<String, String> headers) {
        try {
            long startTime = System.currentTimeMillis();
            String result = OkHttpClient.upload(uploadUrl, content, headers);
            this.uploadFileHandler(result);
            long endTime = System.currentTimeMillis();
            LOGGER.info(" 上传pdf花费时间：{} ms", endTime - startTime);
        } catch (Exception var9) {
            LOGGER.error("上传出错");
            throw new RuntimeException("文件上传错误");
        }
    }

    private void uploadFileHandler(String result) {
        JSONObject json = JSON.parseObject(result);
        if (json.getInteger("errCode").intValue() != 0) {
            String msg = json.getString("msg");
            LOGGER.error("上传出错", msg);
            throw new RuntimeException(msg);
        }
    }
}
