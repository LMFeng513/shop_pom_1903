package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-03 10:58
    @desc:
  */

import com.qf.entity.BackUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface IBackUserService extends UserDetailsService {
    List<BackUser>queryAll();
    int insertUser(BackUser backUser);

    int updateUserRoles(Integer uid, Integer[] rid);
 //  BackUser login(String username,String password);
}
