package com.qf.listener;

  /*
    @author: LMFeng
    @date: 2019-07-13 14:51
    @desc:
  */

import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitListener {

    @Autowired
    private ISearchService iSearchService;

    @RabbitListener(queues = "search_queue")
    public void msgHander(Goods goods){
        iSearchService.addGoods(goods);
    }

}
