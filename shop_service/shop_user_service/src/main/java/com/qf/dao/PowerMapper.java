package com.qf.dao;

  /*
    @author: LMFeng
    @date: 2019-07-03 19:35
    @desc:
  */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Power;

import java.util.List;

public interface PowerMapper extends BaseMapper<Power> {
    List<Power>queryAllPowers();
    List<Power>queryPowerByRid(Integer rid);
}
