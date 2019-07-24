package com.qf.demo;

  /*
    @author: LMFeng
    @date: 2019-07-20 15:35
    @desc:
  */


import com.qf.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class Provier {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel =connection.createChannel();

        //第五种模型 - topic
        channel.exchangeDeclare("myexchange", "fanout");

        //发送消息给交换机
        String content = "Hello Rabbitmq！";
        channel.basicPublish("myexchange", "", null, content.getBytes());

        connection.close();
    }
}
