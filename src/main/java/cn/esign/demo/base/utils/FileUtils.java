package cn.esign.demo.base.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FileUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    private static final int BUFF_SIZE = 1024 * 100;
    private static final int TIME_OUT = 3 * 1000;


    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static File downLoadFromUrl(String urlStr, String fileName, String savePath) throws IOException {
        long startTime = System.currentTimeMillis();
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(TIME_OUT);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        LOGGER.info("花费时间1：" + (System.currentTimeMillis() - startTime) + "ms");
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        LOGGER.info("花费时间2："+(System.currentTimeMillis()-startTime)+"ms");
        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdirs();
        }
        File file = new File(saveDir+File.separator+fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("花费时间4："+(endTime-startTime)+"ms"+"  "+new String (fileName.getBytes(), "UTF-8"));
        LOGGER.info("============");
        return file;
    }


    public static void deleteFile(File file){
        if(file != null){
            file.deleteOnExit();
        }
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[BUFF_SIZE];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    /**
     * 功能：检测当前URL是否可连接或是否有效,
     * 描述：最多连接网络 2 次, 如果 2 次都不成功，视为该地址不可用
     * @param urlStr 指定URL网络地址
     * @return URL
     */
    public static boolean isConnect(String urlStr) {
        int counts = 0;
        if (urlStr == null || urlStr.length() <= 0) {
            return false;
        }

        HttpURLConnection con;
        int state = -1;
        while (counts < 2) {
            try {
                URL url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                state = con.getResponseCode();
                if (state == 200) {
                    LOGGER.info("URL可用！");
                }
                break;
            } catch (Exception ex) {
                counts++;
                LOGGER.info("URL不可用，连接第 "+counts+" 次");
                urlStr = null;
                continue;
            }
        }

        return state == 200;
    }




    /**
     * 根据文件计算出文件的MD5
     *
     * @param file
     * @return
     */
    public static byte[] getFileMD5(File file) {
        if (null == file || !file.isFile()) {
            return null;
        }

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);

            return getFileMD5Base(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }




    /**
     * 根据文件计算出文件的MD5
     *
     * @param in
     * @return
     */
    public static byte[] getFileMD5Base(InputStream in) {
        if (null == in) {
            return null;
        }

        MessageDigest digest = null;
        byte[] buffer = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(digest != null) {
            return digest.digest();
        }
        return null;
    }


    public static byte[] getBytesMD5(byte[] bytes) {
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(bytes, 0, bytes.length);
        } catch (NoSuchAlgorithmException var3) {
            var3.printStackTrace();
        }

        return digest != null ? digest.digest() : null;
    }



}
