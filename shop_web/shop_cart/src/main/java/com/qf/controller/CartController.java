package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-24 17:23
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.qf.aop.IsLogin;
import com.qf.entity.ShopCart;
import com.qf.entity.User;
import com.qf.service.ICartService;
import org.apache.catalina.LifecycleState;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.Cookie;
@Controller
@RequestMapping("/cart")
public class CartController {

    @Reference
    private ICartService cartService;




        /*
        自定义注解+AOP
        添加购物侧*/
        @IsLogin
        @RequestMapping("/insert")
        public String addCart(@CookieValue(value = "carToken",required = false)String cartToken, ShopCart shopCart, User user, HttpServletResponse response){
            System.out.println("添加购物车商品："+cartToken+"---"+user);

            if (cartToken==null){
                cartToken= UUID.randomUUID().toString();
                Cookie cookie =new Cookie("cartToken",cartToken);
                cookie.setMaxAge(60*60*24*365);
                cookie.setPath("/");
                response.addCookie(cookie);

            }
            //添加购物车
            cartService.insertCart(shopCart,user,cartToken);



            return "succ";
        }



        /*查询购物车列表*/


    @IsLogin
    @RequestMapping("/list")
    @ResponseBody
    public String cartList(@CookieValue(value = "cartToken",required = false)String cartToken,User user,String callback){
        List<ShopCart>shopCarts=cartService.queryCartList(user,cartToken);

        return callback != null ? callback+"("+ JSON.toJSONString(shopCarts)+")":JSON.toJSONString(shopCarts);

    }

    @IsLogin
    @RequestMapping("/showlist")
    public String showList(@CookieValue(value = "cartToken",required = false) String cartToken, User user, Model model)
    {
        //查询当前的购物车信息
        List<ShopCart>shopCarts=cartService.queryCartList(user,cartToken);

        //计算总价
        BigDecimal bigDecimal =BigDecimal.valueOf(0.0);
        for (ShopCart shopCart:shopCarts){
            bigDecimal =bigDecimal.add(shopCart.getSprice());
        }
        model.addAttribute("carts",shopCarts);
        model.addAttribute("allprice",bigDecimal.doubleValue());

        return "cartlist";

    }



}
