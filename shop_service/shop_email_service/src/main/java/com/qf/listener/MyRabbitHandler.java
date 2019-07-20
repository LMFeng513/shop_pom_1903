package com.qf.listener;

  /*
    @author: LMFeng
    @date: 2019-07-18 19:30
    @desc:
  */

import com.qf.entity.Email;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MyRabbitHandler {


    private ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEamil;


    @RabbitListener(queues = "email_queue")
    public void handler(Email email){
        executorService.submit(() -> {
            //发送邮件
            MimeMessage mimeMessage =  javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper =new MimeMessageHelper(mimeMessage);

            try {
                messageHelper.setSubject(email.getSubject());

                messageHelper.setFrom(fromEamil);

                messageHelper.setTo(email.getTo());
                messageHelper.setText(email.getContent(),true);

                messageHelper.setSentDate(new Date());

                javaMailSender.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
