package dd.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DemoQuartz {

    public void test(){
        System.out.println(new Date());
    }
}
