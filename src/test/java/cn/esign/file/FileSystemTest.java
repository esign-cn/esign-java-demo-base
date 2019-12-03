package cn.esign.file;

import cn.esign.demo.base.http.OkHttp3Client;
import cn.esign.demo.base.http.OkHttpClient;
import cn.esign.demo.base.provider.FileProvider;
import cn.esign.demo.base.provider.request.FileGetUploadUrlRequest;
import cn.esign.demo.base.provider.response.FileGetUploadUrlResponse;
import cn.esign.demo.base.utils.FileUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhexiu
 * @since 2019/8/2 下午6:38
 */
public class FileSystemTest {

    public static void main(String[] args) throws Exception {
        InputStream inputStream = UploadTest.class.getClassLoader().getResourceAsStream("劳动合同.pdf");
        byte[] body = FileUtils.readInputStream(inputStream);
        uploadFile(body, "劳动合同.pdf");
    }


    public static void uploadFile(byte[] body, String fileName) throws Exception {
        byte[] fileMd5 = FileUtils.getBytesMD5(body);
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedText = encoder.encodeToString(fileMd5);


        Map<String, String> headers = new HashMap<>();
        headers.put("Content-MD5", encodedText);
        headers.put("Content-Type", "application/octet-stream");
        headers.put("X-timevale-project-id", "4438768675");


        String url = "http://oss.esign.cn/1111564083/450b4ca6-a395-4981-bc46-3ff8c52b561a/%E8%A5%BF%E5%AE%89%E5%B9%B4%E6%89%8D%E5%BC%80%E5%A7%8B%E5%86%9C%E4%B8%9A%E6%9C%89%E9%99%90%E5%85%AC%E5%8F%B8.pdf?Expires=1575380351&OSSAccessKeyId=STS.NTrdoF8i23z4xoaYYmiDTpkFC&Signature=me4FICX6sbZ2k5YUHndlRnyYb8w%3D&callback-var=eyJ4OmZpbGVfa2V5IjoiJDBlZmMwODUyLWI1MmQtNDhlYi1hYjg1LTBhZjBlODI5YzE2YiQyMzk0NzczNDg5In0%3D%0A&callback=eyJjYWxsYmFja1VybCI6Imh0dHA6Ly8xMTQuNTUuMjAwLjE0OjgwL2ZpbGUtc3lzdGVtL2NhbGxiYWNrL2FsaW9zcyIsImNhbGxiYWNrQm9keSI6ICJ7XCJtaW1lVHlwZVwiOiR7bWltZVR5cGV9LFwic2l6ZVwiOiAke3NpemV9LFwiYnVja2V0XCI6ICR7YnVja2V0fSxcIm9iamVjdFwiOiAke29iamVjdH0sXCJldGFnXCI6ICR7ZXRhZ30sXCJmaWxlX2tleVwiOiR7eDpmaWxlX2tleX19IiwiY2FsbGJhY2tCb2R5VHlwZSI6ICJhcHBsaWNhdGlvbi9qc29uIn0%3D%0A&security-token=CAIS%2BgF1q6Ft5B2yfSjIr5fHL9Xy1bYThLjfeknQvVk4Zct4n67toTz2IH1FdHZuAOAcv/wylGBX6vsYlqB6T55OSAmcNZAoGhOoRofkMeT7oMWQweEurv/MQBqyaXPS2MvVfJ%2BOLrf0ceusbFbpjzJ6xaCAGxypQ12iN%2B/m6/Ngdc9FHHPPD1x8CcxROxFppeIDKHLVLozNCBPxhXfKB0ca0WgVy0EHsPnvm5DNs0uH1AKjkbRM9r6ceMb0M5NeW75kSMqw0eBMca7M7TVd8RAi9t0t0f0Upmme7ovMWwQBs0rabrDOlNNuKAJid7MmFqsBq%2BPnhXKfQAQKMQudGoABVVpzNBU//P%2BhbICJTmt2EJSxJoOjnB7lHm8L9k9gMAuzKji9QS7KgTApMRjE2F10crS3DUHiDtyL%2Bcn1WmYUmRasLuC/7eHX1%2BBC5S1VMI6ZatTn4V3Jj0dAtaavjJdrWyXrdzaeJ1shVx3p/3IPVMaOwwILLyXiScMRc3%2Bqi70%3D\",\n" +
                "        \"fileKey\": \"$0efc0852-b52d-48eb-ab85-0af0e829c16b$2394773489";
        MediaType mediaType
                = MediaType.parse("application/json; charset=utf-8");
        FileGetUploadUrlRequest request = new FileGetUploadUrlRequest();
        request.setFileName(fileName);
        request.setContentType("application/octet-stream");
        request.setContentMd5(encodedText);
        request.setFileSize((long) body.length);
        String response = OkHttpClient.post(url, RequestBody.create(mediaType, JSON.toJSONBytes(request)), headers);
        System.out.println(response);
        String uploadUrl = JSON.parseObject(response).getString("url");
        uploadFile(body, uploadUrl, headers);
    }

    private static void uploadFile(byte[] content, String uploadUrl, Map<String, String> headers) {
        try {
            long startTime = System.currentTimeMillis();
            String result = OkHttpClient.upload(uploadUrl, content, headers);
            uploadFileHandler(result);
            long endTime = System.currentTimeMillis();
            System.err.println("上传pdf花费时间 "+(endTime - startTime));
        } catch (Exception var9) {


            throw new RuntimeException("文件上传错误");
        }
    }

    private static void uploadFileHandler(String result) {
        JSONObject json = JSON.parseObject(result);
        if (json.getInteger("errCode").intValue() != 0) {
            String msg = json.getString("msg");
            System.err.println("上传出错"+msg);
            throw new RuntimeException(msg);
        }
    }
}
