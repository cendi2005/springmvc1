package dd.util.web;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class WebContext {


    public String getBodyJsonString() {
        return bodyJsonString;
    }


    /**post body json string*/
    private String bodyJsonString;


    public Map<String, String> getHeaders() {
        return headers;
    }

    /**post headers */
    private Map<String,String> headers;


    protected WebContext(){
        System.out.println("init WebContext");
    }




    public  void init(HttpServletRequest request){
        //获取http post body json 字符串
        initParams(request);
        initHeaders(request);
    }

    // 获取body请求体中的json数据
    public  void initParams(HttpServletRequest request){
             bodyJsonString = request.getParameter("name");

//            String bodyJsonString = IOUtils.toString(request.getInputStream(),"utf-8");
    }

    // 获取body请求体中的json数据
    public  void initHeaders(HttpServletRequest request){
        headers = new HashMap<String, String>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String currentHeaderName = headerNames.nextElement();
            headers.put(currentHeaderName,request.getHeader(currentHeaderName));
        }
    }

    private static final ThreadLocal<WebContext> THREAD_LOCAL_CONTEXT = new ThreadLocal<WebContext>(){
        @Override
        protected WebContext initialValue() {
            return new WebContext();
        }
    };

    // 赋值
    public static void setHttpRequestInfo(WebContext httpRequestInfo){
        THREAD_LOCAL_CONTEXT.set(httpRequestInfo);
        System.out.println("execute set");

    }

    //获取
    public static WebContext get(){
        System.out.println("execute get");

        return THREAD_LOCAL_CONTEXT.get();
    }

    // 清空
    public static void remove() {
        THREAD_LOCAL_CONTEXT.remove();
        System.out.println("execute remove");
    }

    public void freeMemory(){
        bodyJsonString = null;
    }



}
