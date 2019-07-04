package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-03 10:58
    @desc:
  */

import com.qf.entity.BackUser;


import java.util.List;

public interface IBackUserService {
    List<BackUser>queryAll();
    int insertUser(BackUser backUser);

    int updateUserRoles(Integer uid, Integer[] rid);
}
