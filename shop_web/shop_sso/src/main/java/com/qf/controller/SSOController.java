package com.qf.controller;

import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    @author: LMFeng
    @date: 2019-07-18 17:15
    @desc:
  */
@Controller
@RequestMapping("/sso")
public class SSOController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;


    private IUserService userService;

    @RequestMapping("/tologin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/toregister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/sendCode")
    public String sendCode(String email){
        String content ="注册验证码为：%d ,如果不是本人操作，请忽略";
        int code    =(int)(Math.random()*9000)+1000;
        Email emailObj =new Email(email,"直达网注册验证码",content);

        redisTemplate.opsForValue().set(email+"_code",code);

        rabbitTemplate.convertAndSend("email_exchange","",emailObj);

        return "succ";
    }


    @RequestMapping("/register")
    public String register(User user, int code){
        Integer sendCode=(Integer)redisTemplate.opsForValue().get(user.getEmail()+"_code");

        //判断验证码是否正确
        if (sendCode ==null ||sendCode !=code){
            return "redisect:/sso/toregister?error=-3";

        }
        //进行注册
        int result =userService.register(user);
    }
}
