package dd.util.http;

import org.apache.commons.codec.CharEncoding;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtils {
    
    public static void main(String[] args) throws Exception{

            Map<String,String> headers = new HashMap<String,String>();
            headers.put("Authorization","");
            headers.put("Content-Type","application/json");
            HttpResponseWrap httpResponseWrap = HttpClientUtils.post("http://11.111.11.1:8888/hello",null,headers,"utf-8");
        System.out.println(httpResponseWrap.getStatusCode()+","+httpResponseWrap.getMessage());

//        HttpResponseWrap httpResponseWrap = HttpClientUtils.post("http://localhost:8888/hello",null,null,"utf-8");

//        System.out.println(httpResponseWrap.getData());
    }




    /**
     *
     * @param url 请求地址
     * @param jsonParameters json格式的参数字符串
     * @param charset 字符集，默认使用utf-8
     * @param headers 消息头设置
     * @return HttpResponseWrap  消息封装体
     */
    public static HttpResponseWrap post(String url, String jsonParameters, Map<String,String> headers, String charset) {


        // http请求包装体
        HttpResponseWrap httpResponseWrap = new HttpResponseWrap();

        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse closeableHttpResponse = null;

        // 设置参数,字符集为utf-8
        if(!StringUtils.isEmpty(jsonParameters)) {
            httpPost.setEntity(new StringEntity(jsonParameters, CharEncoding.UTF_8));
        }

        // 设置消息头
        if(!CollectionUtils.isEmpty(headers)){
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        // 设置超时
        RequestConfig defaultRequestConfig = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.DEFAULT)
                .setExpectContinueEnabled(true)
                .build();
        RequestConfig requestConfig = RequestConfig.copy(defaultRequestConfig)
                .setSocketTimeout(1000)
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(1000)
                .build();
        httpPost.setConfig(requestConfig);


        try {

            closeableHttpResponse = httpclient.execute(httpPost);


            System.out.println("after http:"+closeableHttpResponse);

            // http响应码
            int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
            httpResponseWrap.setStatusCode(statusCode);
            // 获取响应实体
            HttpEntity httpEntity = closeableHttpResponse.getEntity();
            // 根据输入流解析获取json格式数据
            String data = EntityUtils.toString(httpEntity);
            httpResponseWrap.setData(data);

            //关闭资源
            EntityUtils.consume(httpEntity);

        } catch (IOException e) {
            System.out.println("http 请求出错"+e.getMessage());
            httpResponseWrap.setStatusCode(400);
            httpResponseWrap.setMessage(e.getMessage());
            e.printStackTrace();
        }finally {
            // 关闭连接和资源
            httpPost.releaseConnection();
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(closeableHttpResponse!=null){
                    closeableHttpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return httpResponseWrap;
    }


}
