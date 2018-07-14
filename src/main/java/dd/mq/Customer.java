package dd.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.util.Date;

public class Customer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println(new Date());
    }
}
