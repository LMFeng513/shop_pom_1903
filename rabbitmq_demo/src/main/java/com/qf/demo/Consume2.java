package com.qf.demo;

  /*
    @author: LMFeng
    @date: 2019-07-20 15:33
    @desc:
  */


import com.qf.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Consume2 {

    public static void main(String[]args) throws IOException {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare("myqueue2", false, false ,false ,null);

        //进行队列和交换机的绑定
        channel.queueBind("myqueue2", "myexchange", "");//队列绑定交换机
//        channel.exchangeBind();//交换机绑定交换机

        //消费者去监听这个队列
        channel.basicConsume("myqueue2", true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2获得消息：" + new String(body));
            }
        });

    }
}
