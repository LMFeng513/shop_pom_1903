package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-24 17:23
    @desc:
  */

import com.qf.aop.IsLogin;
import com.qf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

        /*添加购物侧*/
    @IsLogin
        @RequestMapping("/insert")
        public String addCart(Integer gid, Integer gnumber, User user){
            System.out.println("添加购物车商品："+gid+" "+gnumber);
            return null;
        }

}
