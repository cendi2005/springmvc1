package dd.util;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class HttpClientUtils {
    
    public static void main(String[] args) throws Exception{

    }




    /**
     *
     * @param url 请求地址
     * @param jsonParameters json格式的参数字符串
     * @param charset 字符集，默认使用utf-8
     * @return HttpResponseWrap  消息封装体
     */
    public static HttpResponseWrap post(String url, String jsonParameters, String charset) {

        // http请求包装体
        HttpResponseWrap httpResponseWrap = new HttpResponseWrap();

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse closeableHttpResponse = null;
        // 设置参数
        httpPost.setEntity(new StringEntity(jsonParameters, CharEncoding.UTF_8));

        // 设置超时
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .build();
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
                .setSocketTimeout(3000)
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpPost.setConfig(requestConfig);


        try {
            closeableHttpResponse = httpclient.execute(httpPost);

            // http响应码
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            httpResponseWrap.setStatusCode(statusCode);

            // 获取响应实体
            HttpEntity httpEntity = closeableHttpResponse.getEntity();

            // 根据输入流解析获取json格式数据
            String data = IOUtils.toString(httpEntity.getContent(),"utf-8");
            httpResponseWrap.setData(data);

            //关闭
            EntityUtils.consume(httpEntity);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭连接
            try {
                closeableHttpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return httpResponseWrap;
    }


}