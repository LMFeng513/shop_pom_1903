package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-18 20:19
    @desc:
  */

import com.qf.entity.User;

public interface IUserService {
    int register (User user);

    User queryByUserName(String username);

    int updatePassword(String username,String password);



}
