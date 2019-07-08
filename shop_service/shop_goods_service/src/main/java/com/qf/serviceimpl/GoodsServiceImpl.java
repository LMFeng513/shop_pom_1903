package com.qf.serviceimpl;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:12
    @desc:
  */


import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.GoodsMapper;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<Goods> queryGoodsList() {
        return goodsMapper.selectList(null);
    }

    @Override
    public List<Goods> toList(){
        List<Goods>goods=goodsMapper.toList();
        return goods;
    }

    @Override
    public Goods insertGoods(Goods goods) {
        goodsMapper.insert(goods);
        return goods;
    }
}
