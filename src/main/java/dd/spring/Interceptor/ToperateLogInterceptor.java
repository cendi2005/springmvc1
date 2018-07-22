package dd.spring.Interceptor;

import org.springframework.core.NamedThreadLocal;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToperateLogInterceptor implements HandlerInterceptor {



    private NamedThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<Long>("StopWatch-StartTime");

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        long beginTime = System.currentTimeMillis();

        startTimeThreadLocal.set(beginTime);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        long endTime = System.currentTimeMillis();

        long beginTime = startTimeThreadLocal.get();

        long consumeTime = endTime - beginTime;
//        if (consumeTime > 500) {
            //TODO 记录到日志文件
            System.out.println(
                    String.format("%s consume %d millis", request.getRequestURI(), consumeTime));
//        }

    }
}
