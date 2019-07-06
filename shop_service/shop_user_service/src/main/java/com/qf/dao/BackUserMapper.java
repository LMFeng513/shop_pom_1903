package com.qf.dao;

  /*
    @author: LMFeng
    @date: 2019-07-02 19:49
    @desc:
  */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qf.entity.BackUser;

public interface BackUserMapper extends BaseMapper<BackUser> {
BackUser queryByUserName(String username);
}
