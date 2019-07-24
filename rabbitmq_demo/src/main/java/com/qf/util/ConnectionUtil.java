package com.qf.util;

  /*
    @author: LMFeng
    @date: 2019-07-20 15:21
    @desc:
  */


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {
    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("39.108.188.42");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");



    }
    public static Connection getConnection(){

        Connection connection =null;
        try {
            connection =connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    return connection;
    }


}
