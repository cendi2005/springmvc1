package dd.spring.Interceptor;

import dd.util.web.WebContext;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class BaseInterceptor implements HandlerInterceptor{

    /**
     * 在DispatcherServlet之前执行
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {


        WebContext webContext = WebContext.get();
        webContext.init(request);
        System.out.println("name:"+webContext.getBodyJsonString());

        Map<String,String> headers =  webContext.getHeaders();
        System.out.println("************请求头参数**********");
        System.out.println("************请求头参数**********");
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }



        System.out.println("************BaseInterceptor preHandle executed**********");
        return true;
    }

    /**
     * 在controller执行之后的DispatcherServlet之后执行
     * */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        System.out.println("************BaseInterceptor postHandle executed**********");
    }

    /**
     * 在页面渲染完成返回给客户端之前执行
     * */

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        WebContext webContext = WebContext.get();
        webContext.freeMemory();
        System.out.println("************BaseInterceptor afterCompletion executed**********");
    }

}