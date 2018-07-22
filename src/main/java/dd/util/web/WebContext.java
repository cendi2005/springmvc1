package dd.util.web;

import dd.spring.Interceptor.RequestDetail;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
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



    private RequestDetail requestDetail;
    public RequestDetail getRequestDetail() {
        return requestDetail;
    }


    protected WebContext(){
        System.out.println("init WebContext");
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
        headers = null;
        requestDetail = null;
    }




    public  void init(HttpServletRequest request){
        //获取http post body json 字符串
        initParams(request);
        initHeaders(request);
        initRequestInfo(request);
    }


    private void initRequestInfo(HttpServletRequest request){
        requestDetail = new RequestDetail();
        //获取本地IP，即服务器IP
        String localAddr = request.getLocalAddr();
        requestDetail.setLocalAddr(localAddr);

        //获取本地名称，即服务器名称
        String localName = request.getLocalName();
        requestDetail.setLocalName(localName);
        //获取本地端口号，即Tomcat端口号
        int localPort = request.getLocalPort();
        requestDetail.setLocalPort(localPort);
        //用户的语言环境
        Locale locale = request.getLocale();
        requestDetail.setLocale(locale);
        //context路径
        String contextPath = request.getContextPath();
        requestDetail.setContextPath(contextPath);
        //GET还是POST
        String method = request.getMethod();
        requestDetail.setMethod(method);
        //协议，http协议
        String protocol = request.getProtocol();
        requestDetail.setProtocol(protocol);
        //查询字符串
        String queryString = request.getQueryString();
        requestDetail.setQueryString(queryString);
        //远程IP，即客户端IP
        String remoteAddr = request.getRemoteAddr();
        requestDetail.setRemoteAddr(remoteAddr);
        //远程端口，即客户端端口
        int remotePort = request.getRemotePort();
        requestDetail.setRemotePort(remotePort);
        //远程用户
        String remoteUser = request.getRemoteUser();
        requestDetail.setRemoteUser(remoteUser);
        //客户端的Session的ID
        String requestedSessionId = request.getRequestedSessionId();
        requestDetail.setRequestedSessionId(requestedSessionId);
        //用户请求的URL
        String requestURI = request.getRequestURI();
        requestDetail.setRequestURI(requestURI);
        //协议头，例如http
        String scheme = request.getScheme();
        requestDetail.setScheme(scheme);
        //服务器名称
        String serverName = request.getServerName();
        requestDetail.setServerName(serverName);

//        int serverPort = request.getServerPort();//服务器端

        //Servlet路径
        String servletPath = request.getServletPath();
        requestDetail.setServletPath(servletPath);

        String s = requestDetail.toString();
        System.out.println(s);

    }


}
