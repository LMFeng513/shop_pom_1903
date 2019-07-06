package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:04
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

        @Reference
        private IGoodsService goodsService;

        @RequestMapping("/list")
        public String goodslist(Model model) {
            List<Goods> goodsList = goodsService.queryGoodsList();
           model.addAttribute("goods",goodsList);
            return "goodslist";
        }
}
