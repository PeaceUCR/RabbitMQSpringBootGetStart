package com.example.demo.config;


import com.example.demo.mq.Receiver;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//The queue() method creates an AMQP queue. The exchange() method creates a topic exchange.
// The binding() method binds these two together, defining the behavior that occurs when RabbitTemplate publishes to an exchange.
@Component
public class RabbitMQConfig {

    public static final String queueName = "my-frist-rabbit";

    public static final String topicExchangeName = "my-rabbit-exchange";
    //config queue
    @Bean
    Queue queue(){
        return new Queue(queueName, false)  ;
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    //config binding
    //https://spring.io/blog/2010/06/14/understanding-amqp-the-protocol-used-by-rabbitmq/

    //What does this have to do with binding? One of the standard headers is called routing-key and it is this that the broker uses to match messages to queues. Each queue specifies a "binding key" and if that key matches the value of the routing-key header, the queue receives the message.

    //autowire with componenet
    //https://stackoverflow.com/questions/32078600/why-do-i-not-need-autowired-on-bean-methods-in-a-spring-configuration-class
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
    }


    //The message listener container and receiver beans are all you need to listen for messages.
    // To send a message, you also need a Rabbit template.

    //The bean defined in the listenerAdapter() method is registered as a message listener in the container defined in container().
    // It will listen for messages on the "my-frist-rabbit" queue. Because the Receiver class is a POJO, it needs to be wrapped in the MessageListenerAdapter, where you specify it to invoke receiveMessage.

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }


}
