package com.qf.serviceimpl;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:12
    @desc:
  */


import com.qf.dao.GoodsMapper;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> queryGoodsList() {
        return goodsMapper.selectList(null);
    }
}
