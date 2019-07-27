package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-27 9:54
    @desc:
  */

import com.qf.entity.Order;
import com.qf.entity.User;

import java.util.List;

public interface IOderService {
    int insertOrder (Integer aid , User user);

List<Order>queryByUid(Integer uid);
Order queryByOid(String orderrid);
}
