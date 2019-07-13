package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-13 8:47
    @desc:
  */

import com.qf.entity.Goods;

import java.util.List;

public interface ISearchService {
    List<Goods>searchByKey(String keyword);

    int addGoods(Goods goods);
}
