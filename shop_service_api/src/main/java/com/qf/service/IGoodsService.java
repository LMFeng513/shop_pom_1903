package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:06
    @desc:
  */

import com.qf.entity.Goods;

import java.util.List;

public interface IGoodsService {
   List<Goods> queryGoodsList();
    Goods insertGoods(Goods goods);

    Goods queryById(Integer gid);
   // List<Goods>list();
}
