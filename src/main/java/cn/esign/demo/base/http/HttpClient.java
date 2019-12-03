package cn.esign.demo.base.http;

public interface HttpClient<T> {

    /**
     * 获取API请求实例
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T getApi(Class<T> clazz);

    /**
     * 获取API请求实例
     * @param clazz
     * @param isHeader 是否需要填充默认header
     * @param <T>
     * @return
     */
    <T> T getApi(Class<T> clazz, boolean isHeader);
}
