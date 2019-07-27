package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-27 15:47
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.aop.IsLogin;
import com.qf.entity.Address;
import com.qf.entity.ShopCart;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import com.qf.service.ICartService;
import com.qf.service.IOderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Reference
    private ICartService cartService;

    @Reference
    private IOderService oderService;

    @Reference
    private IAddressService addressService;


    /*去到订单编辑页面
    *
    * bug:
    * 未登录-》2件商品-》查看购物车（2件商品）-》下单-》强制登录（购物车合并）-》下单*/

    @IsLogin(mustLogin = true)
    @RequestMapping("/edit")
    public String toOrderEdit(User user, Model model){
        //购物车的所有商品
        List<ShopCart>shopCarts =cartService.queryCartList(user,null);

        //当前用户的所以收货地址
        List<Address>addresses=addressService.queryByUid(user.getId());

        //计算总价
        BigDecimal bigDecimal =BigDecimal.valueOf(0.0);
        for (ShopCart shopCart:shopCarts){
            bigDecimal =bigDecimal.add(shopCart.getSprice());
        }
        model.addAttribute("carts",shopCarts);
        model.addAttribute("addresses",addresses);
        model.addAttribute("allprice",bigDecimal.doubleValue());

        return "orderedit";

    }

    @IsLogin(mustLogin = true)
    @RequestMapping("/insertOrder")
    public String insertOrder(Integer aid,User user){
        oderService.insertOrder(aid,user);
        return "succ";
    }


}
