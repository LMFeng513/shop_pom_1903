package com.qf.dao;

  /*
    @author: LMFeng
    @date: 2019-07-08 8:34
    @desc:
  */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Goods;
import com.qf.entity.Gtype;

import java.util.List;

public interface GtypeMapper extends BaseMapper<Gtype> {
    List<Gtype> toList();

    Gtype queryById(Integer id);

}


