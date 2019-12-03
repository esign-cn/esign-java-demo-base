
package cn.esign.demo.base.http;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import okhttp3.ConnectionPool;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.OkHttpClient.Builder;
import org.apache.commons.lang3.StringUtils;

public class OkHttpClient {
    public static final okhttp3.OkHttpClient CLIENT = okHttpClient();

    public OkHttpClient() {
    }

    public static okhttp3.OkHttpClient okHttpClient() {
        return (new Builder()).readTimeout(60L, TimeUnit.SECONDS).connectTimeout(60L, TimeUnit.SECONDS).writeTimeout(120L, TimeUnit.SECONDS).connectionPool(new ConnectionPool(100, 10L, TimeUnit.MINUTES)).build();
    }

    public static String get(String url, Map<String, String> headers) throws IOException {
        okhttp3.Request.Builder builder = (new okhttp3.Request.Builder()).url(url);
        return process(builder, headers);
    }

    public static String upload(String url, byte[] data, Map<String, String> headers) throws IOException {
        String contentType = getContentType(headers);
        RequestBody body = getBytesBody(contentType, data);
        return put(url, body, headers);
    }

    public static String upload(String url, File file, Map<String, String> headers) throws IOException {
        String contentType = getContentType(headers);
        RequestBody body = getFileBody(contentType, file);
        return put(url, body, headers);
    }

    private static RequestBody getBytesBody(String contentType, byte[] data) {
        return RequestBody.create(MediaType.parse(contentType), data);
    }

    private static RequestBody getFileBody(String contentType, File file) {
        return RequestBody.create(MediaType.parse(contentType), file);
    }

    public static String getContentType(Map<String, String> headers) {
        String contentType =  headers.get("Content-Type");
        if (StringUtils.isBlank(contentType)) {
            contentType = "application/octet-stream";
        }

        return contentType;
    }

    public static String put(String url, RequestBody body, Map<String, String> headers) throws IOException {
        okhttp3.Request.Builder builder = (new okhttp3.Request.Builder()).url(url).put(body);
        return process(builder, headers);
    }


    public static String post(String url, RequestBody body, Map<String, String> headers) throws IOException {
        okhttp3.Request.Builder builder = (new okhttp3.Request.Builder()).url(url).post(body);
        return process(builder, headers);
    }

    private static String process(okhttp3.Request.Builder builder, Map<String, String> headers) throws IOException {
        if (headers != null && headers.size() > 0) {
            Iterator iterator = headers.entrySet().iterator();

            while (iterator.hasNext()) {
                Entry<String, String> header = (Entry) iterator.next();
                builder.addHeader( header.getKey(), header.getValue());
            }
        }

        Request request = builder.build();
        Response response = CLIENT.newCall(request).execute();
        return response.body().string();
    }
}
