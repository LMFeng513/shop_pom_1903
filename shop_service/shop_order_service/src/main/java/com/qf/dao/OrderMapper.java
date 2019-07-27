package com.qf.dao;

  /*
    @author: LMFeng
    @date: 2019-07-27 10:18
    @desc:
  */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper extends BaseMapper<Order> {
    int insertOrder(@Param("order")Order order,@Param("tableIndex")int tableIndex);
}
