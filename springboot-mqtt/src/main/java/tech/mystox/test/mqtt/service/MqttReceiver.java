package tech.mystox.test.mqtt.service;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import static tech.mystox.test.mqtt.config.MqttConfig.CHANNEL_NAME_IN;

/**
 * Created by mystoxlol on 2019/10/25, 17:16.
 * company: kongtrolink
 * description:
 * update record:
 */
@MessageEndpoint
public class MqttReceiver {

    /*@ServiceActivator(inputChannel = CHANNEL_NAME_IN)
    public MessageHandler handler() {
        return new MessageHandler() {

            @Override
            public void handleMessage(Message<?> message) throws MessagingException {


                System.out.println(message);
                try {
                    Thread.sleep(20000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }*/
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_IN,async = "true")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println(message);
                try {
                    Thread.sleep(20000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("end");
//                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
//                String type = topic.substring(topic.lastIndexOf("/")+1, topic.length());
//                if("hello".equalsIgnoreCase(topic)){
//                    System.out.println("hello,fuckXX,"+message.getPayload().toString());
//                }else if("hello1".equalsIgnoreCase(topic)){
//                    System.out.println("hello1,fuckXX,"+message.getPayload().toString());
//                }
            }
        };
    }


}
