package dd.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class PostConstructBean {
    @PostConstruct
    public void init(){
        System.out.println("PostConstruct");
    }
    @PreDestroy
    public void destory(){
        System.out.println("PreDestroy");
    }
}
