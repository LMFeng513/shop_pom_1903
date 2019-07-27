package com.qf.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.qf.entity.Email;
import com.qf.entity.User;
import com.qf.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


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

    @Reference
    private IUserService userService;

    @RequestMapping("/tologin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/toregister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("/sendCode")
    @ResponseBody
    public String sendCode(String email) {
        System.out.println(email);
        //设置邮箱的内容
        String content = "注册验证码为：%d ,如果不是本人操作，请忽略";
        //生成随机验证码
        int code = (int)(Math.random() * 9000) + 1000;
        //输出内容和验证吗
        content =String.format(content,code);
        //设置邮箱标题
        Email emailObj = new Email(email, "直达网注册验证码", content);
        //放进Redis
        redisTemplate.opsForValue().set(email + "_code", code);

        System.out.println(emailObj);
        //发送邮件
        rabbitTemplate.convertAndSend("email_exchange", "", emailObj);

        return "succ";
    }


    @RequestMapping("/register")
    public String register(User user, int code) {
        //拿到注册用户邮箱的验证码
        Integer sendCode = (Integer) redisTemplate.opsForValue().get(user.getEmail() + "_code");

        //判断验证码是否正确
        if (sendCode == null || sendCode != code) {
            return "redisect:/sso/toregister?error=-3";

        }
        //进行注册
        int result = userService.register(user);

        if (result > 0) {
            return "redirect:/sso/tologin";

        }

        return "redirect:/sso/toregister?error=" + result;
    }

    /*发送找回密码的邮件*/
    @RequestMapping("/sendPashMail")
    @ResponseBody
    public Map<String, Object>sendPassMail(String username){

        Map<String ,Object>map=new HashMap<>();
        //根据用户名查询用户的邮箱
        User user=userService.queryByUserName(username);

        if (user ==null) {
        //用户不存在
            map.put("code","1000");
            return map;
        }
        //用户存在，给用户的邮箱发送邮件

        //生成一个修改密码的凭证，防止出现信息安全问题
        String token= UUID.randomUUID().toString();

        //将凭证放入Redis中
        redisTemplate.opsForValue().set(username+"_token",token);
        redisTemplate.expire(username+"_token",5, TimeUnit.MINUTES);

        //生成一个url
        String url ="http://localhost:8084/sso/toChangePassword?username="+username+"&token="+token;

        Email email =new Email(user.getEmail(),"直达网找回密码邮件",
                "找回密码请点击<a href='"+url+"'>这里</a>");

        //发送邮件
        rabbitTemplate.convertAndSend("email_exchange","",email);

        //邮件发送成功
        String emailStr=user.getEmail();
        int index = emailStr.indexOf("@");

        String emailStr2 = emailStr.replace(emailStr.substring(3, index), "********");
        //设置去邮箱的地址
        String goMail="mail."+emailStr.substring(index+1);


        map.put("code","0000");
        map.put("emailStr",emailStr2);
        map.put("goEmail",goMail);
        return map;

    }

    //去到修改密码的页面
    @RequestMapping("/tochangePasseord")
    public String toChangePassword(){
        return "changgePassword";
    }

    //修改密码
    @RequestMapping("/changePassword")
    public String changePassword(String password,String username,String token){
        //校验token是否合法
        String uToken= (String) redisTemplate.opsForValue().get(username + "_token");

        //判断token是否相同
        if(token.equals(uToken)){
            userService.updatePassword(username,password);

            //删除token凭证，安全性的提现，防止利用来二次修改，
            redisTemplate.delete(username+"_token");

            return "redirect:/sso/tologin";
        }

        return "fail";
    }

    @RequestMapping("/login")

    public String login(User user,String returnUrl, HttpServletResponse response){
    user=userService.login(user);

    if (user ==null){
        return "redirect:/sso/tologin?error=1";
    }

    if (returnUrl ==null||returnUrl==""){
        return "redirect://sso/tologin?8081";
    }
    //成功
        String token=UUID.randomUUID().toString();
    redisTemplate.opsForValue().set(token,user);
    redisTemplate.expire(token,7,TimeUnit.DAYS);

    //将令牌写入流浪器的cookie中
        Cookie cookie =new Cookie("user_token",token);
        cookie.setMaxAge(60*60*24*7);
        //设置cookie的有效路径，任何请求都可以访问
        cookie.setPath("/");
        //设置cookie的有效域名，Diamonds
        response.addCookie(cookie);


    return "redirect";
    }

    @RequestMapping("/checklogin")
    @ResponseBody
    @CrossOrigin
    public String checkLogin(@CookieValue(name = "loginToken",required = false)String loginToken,String callback){
        User user =null;
        if (loginToken !=null){
            user = (User) redisTemplate.opsForValue().get(loginToken);
        }
        String userJson =user !=null ? JSON.toJSONString(user):null;
        return callback !=null?callback+"("+userJson+")":userJson;
    }
    @RequestMapping("/logout")
    public String logout(@CookieValue(name = "loginToken",required = false)String loginToken,HttpServletResponse response){

        if (loginToken!= null){
            redisTemplate.delete(loginToken);
        }
        Cookie cookie =new Cookie("loginToken","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redriect:/sso/tologin";
    }

}