package com.qf.dao;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:14
    @desc:
  */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Goods;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    List<Goods> toList();
}
