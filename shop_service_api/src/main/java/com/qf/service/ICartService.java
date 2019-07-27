package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-27 9:51
    @desc:
  */

import com.qf.entity.ShopCart;
import com.qf.entity.User;

import java.util.List;

public interface ICartService {

    int insertCart(ShopCart shopCart, User user,String cartToken);

    List<ShopCart>queryCartList(User user,String cartToken);

    int mergeCarts(String cartToken,User user);
}
