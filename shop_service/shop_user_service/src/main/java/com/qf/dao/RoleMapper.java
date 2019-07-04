package com.qf.dao;

  /*
    @author: LMFeng
    @date: 2019-07-03 19:35
    @desc:
  */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<Role>queryListByUid(Integer uid);

}
