package dd.mq;

import org.apache.commons.io.IOUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;

public class Customer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try {
            System.out.println(IOUtils.toString(message.getBody(),"utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
