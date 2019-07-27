package com.qf.dao;

  /*
    @author: LMFeng
    @date: 2019-07-27 10:11
    @desc:
  */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Order;
import com.qf.entity.OrderDetils;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderDetilsMapper extends BaseMapper<OrderDetils> {

int insertDetils(@Param("orderDetils")List<OrderDetils>orderDetils,
                 @Param("tableIndex")int tableIndex);

}
