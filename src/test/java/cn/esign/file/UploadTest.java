package cn.esign.file;

import cn.esign.demo.base.client.EsignClient;
import cn.esign.demo.base.facade.FileFacade;
import cn.esign.demo.base.utils.FileUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhexiu
 * @since 2019/8/2 下午6:33
 */
public class UploadTest {

    public static void main(String[] args) throws IOException {
        new EsignClient().build();
        FileFacade fileFacade = new FileFacade();
        InputStream inputStream = UploadTest.class.getClassLoader().getResourceAsStream("劳动合同.pdf");

        byte[] body = FileUtils.readInputStream(inputStream);
        String fileId = fileFacade.uploadFile(body, "劳动合同.pdf");
        System.out.println(fileId);
    }
}
