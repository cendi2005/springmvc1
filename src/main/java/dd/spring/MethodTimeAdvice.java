package dd.spring;

import dd.util.web.WebContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.logging.log4j.LogManager;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *用来监控方法的执行时间--
 */
public class MethodTimeAdvice implements MethodInterceptor {

    static org.apache.logging.log4j.Logger logger = LogManager.getLogger(MethodTimeAdvice.class.getName());

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        WebContext webContext = WebContext.get();
        StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        Object result = null;
        //监控的类名
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();
        //监控的方法名
        String methodName = className + "." + invocation.getMethod().getName();
        try {
            //这个是我们监控的bean的执行并返回结果
            result = invocation.proceed();
        } catch (Throwable e) {
            //监控的参数
            Object[] objs = invocation.getArguments();
            logger.error("执行异常,方法名：" + methodName + "参数:" + getString(objs), e);
            throw e;
        }
        clock.stop(); //计时结束


//            logger.info(webContext.getRequestDetail().getRequestURI());
            logger.info(webContext.getBodyJsonString());
            logger.info(clock.prettyPrint());
            logger.info("执行时间:" + clock.getTotalTimeSeconds() + " ms [" + methodName + "]");

        return result;
    }

    /**
     * 这个类主要是用于输出方法的参数
     *
     * @param objs
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getString(Object[] objs) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0, len = objs.length; i < len; i++) {
            if (objs[i] instanceof String) {
                stringBuffer.append("String类型：" + objs[i].toString());
            } else if (objs[i] instanceof Map) {
                HashMap<String, Object> hashMap = (HashMap<String, Object>) objs[i];
                HashMap<String, Object> map = hashMap;
                HashSet<String> set = (HashSet<String>) map.keySet();
                stringBuffer.append("Map类型");
                for (String str : set) {
                    stringBuffer.append(str + "=" + map.get(str));
                }
            } else if (objs[i] instanceof Integer) {
                stringBuffer.append("整数类型：");
                stringBuffer.append(objs[i].toString());
            } else {
                stringBuffer.append(objs[i].toString());
            }
        }
        return stringBuffer.toString();
    }
}