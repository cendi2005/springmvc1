package dd.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/***
 *
 * 获取spirng应用上下文
 *
 */
@Component
public class ApplicationContextAwareDemo implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
        System.out.println("applicationContext:"+applicationContext);
    }
}
