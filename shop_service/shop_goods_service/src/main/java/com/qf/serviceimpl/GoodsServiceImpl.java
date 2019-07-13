package com.qf.serviceimpl;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:12
    @desc:
  */


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.GoodsMapper;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import com.qf.service.ISearchService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Reference
    private ISearchService searchService;
    @Override
    public List<Goods> queryGoodsList() {
        return goodsMapper.selectList(null);
    }

//    @Override
//    public List<Goods> list(){
//        List<Goods>goods=goodsMapper.list();
//        return goods;
//    }

    @Override
    public Goods insertGoods(Goods goods) {
//        goodsMapper.insert(goods);
//
//       searchService.addGoods(goods);
        goodsMapper.insert(goods);
        //将商品添加到数据库
       // searchService.addGoods(goods);


        rabbitTemplate.convertAndSend("goods_exchange","",goods);



        return goods;
    }

@Override
    public Goods queryById(Integer gid){
        return  goodsMapper.selectById(gid);
}
}
