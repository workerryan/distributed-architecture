默认情况下，消费者是通过轮询方式进行消费的。受硬件条件，每个消费者可能处理的能力不一样，所以默认的均摊消费可能不合适 <br>

所以，我们可能需要进行公平消费（也就是工作队列），从而实现 ”能者多劳“ 。通过手动应答的方式可以实现这个功能。公平队列就是谁应答快谁就多劳。

在`channel`有个`basicQos(int num)`方法，该方法就是MQ一次可以推送多少消息给消费者，通常设置`channel.basicQos(1)`来保证消费者一次只会接收一条消息。换句话说，只有在消费者空闲的时候会发送下一条信息。调度分发消息的方式，也就是告诉RabbitMQ每次只给消费者处理一条消息，也就是等待消费者处理完毕并自己对刚刚处理的消息进行确认之后，才发送下一条消息，防止消费者太过于忙碌，也防止它太过去清闲。

下面代码是常见的消费者的代码：

```java
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 * @author wanglei
 * @since 1.0.0
 */
public class Customer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.获取连接
        Connection newConnection = MQConnectionUtils.newConnection();
        // 2.获取通道
        Channel channel = newConnection.createChannel();
        channel.queueDeclare(Producer.QUEUE_NAME, 
                             false, false, false, null);
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, 
                                       Envelope envelope, 
                                       AMQP.BasicProperties properties, 
                                       byte[] body) throws IOException {
                String msgString = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msgString);
                //手动应答
                channel.basicAck(envelope.getDeliveryTag(), true);
            }
        };
        // 3.监听队列，第2个参数为false就是手动应答
        channel.basicConsume(Producer.QUEUE_NAME, false, defaultConsumer);
    }
}
```

