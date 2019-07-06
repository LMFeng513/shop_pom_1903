package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-03 19:30
    @desc:
  */

import com.qf.entity.Power;

import java.util.List;

public interface IPowerService {

List<Power>powerList();
int insert(Power power);


    List<Power> powerListByRid(Integer rid);
}
