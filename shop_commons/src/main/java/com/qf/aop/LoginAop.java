//package com.qf.aop;
//
//  /*
//    @author: LMFeng
//    @date: 2019-07-24 19:09
//    @desc:
//  */
//
//import com.qf.entity.User;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import java.io.UnsupportedEncodingException;
//import java.lang.reflect.Method;
//import java.net.URLEncoder;
//
//@Aspect
//@Component
//public class LoginAop {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    //切点表达式——告诉spring这个放法事去环绕那个目标方法的
//    @Around("@annotation(IsLogin)")
//    public Object handlLogin(ProceedingJoinPoint proceedingJoinPoint) {
//
//        //获得cookiie
//        //获得request
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//
//        //通过request获得cookie
//        String loginToken = null;
//
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("loginToken")) {
//                    loginToken = cookie.getValue();
//                    break;
//                }
//            }
//
//        }
//        User loginUser = null;
//        //通过凭证redis 中获得用户信息
//        if (loginToken != null) {
//            loginUser = (User) redisTemplate.opsForValue().get(loginToken);
//        }
//        //判断是否登陆
//        if (loginUser == null) {
//            //当前未登录，判断IsLogin注解中的mustLogin放法是否为true,
//            //如果为true 强制跳转到登录页面，如果为false则不作处理
//
//
//            //获得增强的目标签名
//            MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//
//            //获得目标方法的Method
//            Method method = signature.getMethod();
//
//            //获得方法上的注解
//            IsLogin isLogin = method.getAnnotation(IsLogin.class);
//
//            if (isLogin.mustLogin()){
//                //获取当前请求的url
//                String url=request.getRequestURI().toString();
//                try {
//                    url = URLEncoder.encode(url,"UTF-8");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//                return "reqirect://http://localhost:8084/sso/tologin?returnUrl="+url;
//
//            }
//
//        }
//        //已经登录，或者不用强制登录
//        //修改目标方法的实参列表
//        //获得目标方法的时机参数列表o
//        Object[]args=proceedingJoinPoint.getArgs();
//        for (int i=0;i<args.length;i++){
//            if (args!=null  &&args[i].getClass()==User.class){
//                //覆盖原来的参数孩子
//                args[i] =loginUser;
//                break;
//            }
//        }
//        //用我的实参列表调用目标方法
//        Object result=null;
//        try {
//            result =proceedingJoinPoint.proceed(args);
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return request;
//    }
//}
