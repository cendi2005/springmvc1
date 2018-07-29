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

public class BaseInterceptor2 implements HandlerInterceptor {

    static Logger logger = LogManager.getLogger(BaseInterceptor2.class.getName());

    @Resource
    private ExampleBean exampleBean;

    /**
     * 在DispatcherServlet之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
       logger.info("2 ************BaseInterceptor2 preHandle executed**********");
        return true;
    }

    /**
     * 在controller执行之后的DispatcherServlet之后执行
     */
    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
        WebContext webContext = WebContext.get();
        logger.info("************BaseInterceptor2:"+webContext.getBodyJsonString());
    }

    /**
     * 在页面渲染完成返回给客户端之前执行
     */

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        WebContext webContext = WebContext.get();
        logger.info("************BaseInterceptor2:"+webContext.getBodyJsonString());
    }

}