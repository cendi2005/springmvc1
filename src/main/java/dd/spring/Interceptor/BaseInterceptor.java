package dd.spring.Interceptor;

import dd.spring.ExampleBean;
import dd.util.web.WebContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BaseInterceptor implements HandlerInterceptor {

    static Logger logger = LogManager.getLogger(BaseInterceptor.class.getName());

    @Resource
    private ExampleBean exampleBean;

    /**
     * 在DispatcherServlet之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {


        WebContext webContext = WebContext.get();
        webContext.init(request);


        RequestDetail requestDetail = webContext.getRequestDetail();
        String path = requestDetail.getQueryString();

        List<String> whiteList = exampleBean.getWhiteList();
        if (whiteList.contains(path)) {
            logger.info("白名单");
        } else {
            logger.info("黑名单");
        }


//       logger.info("name:" + webContext.getBodyJsonString());
//
//        Map<String, String> headers = webContext.getHeaders();
//       logger.info("************请求头参数**********");
//       logger.info("************请求头参数**********");
//       logger.info("asdfasfasdf");
//        for (Map.Entry<String, String> entry : headers.entrySet()) {
//           logger.info(entry.getKey() + "," + entry.getValue());
//        }


       logger.info("1 ************BaseInterceptor preHandle executed**********");
        return true;
    }

    /**
     * 在controller执行之后的DispatcherServlet之后执行
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
       logger.info("************BaseInterceptor postHandle executed**********");
    }

    /**
     * 在页面渲染完成返回给客户端之前执行
     */

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        WebContext webContext = WebContext.get();
        webContext.freeMemory();
       logger.info("************BaseInterceptor afterCompletion executed**********");
    }

}