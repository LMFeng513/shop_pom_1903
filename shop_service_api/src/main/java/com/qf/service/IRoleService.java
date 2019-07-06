package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-03 19:31
    @desc:
  */

import com.qf.entity.Role;

import java.util.List;

public interface IRoleService {
    List<Role> roleList();
    int insertRole(Role role);
    List<Role>roleListByUi(Integer uid);

    int updateRolePowers(Integer rid, Integer[] pids);
}
