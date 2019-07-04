package com.qf.serviceimpl;

  /*
    @author: LMFeng
    @date: 2019-07-03 19:40
    @desc:
  */


import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.RoleMapper;
import com.qf.entity.Role;
import com.qf.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> roleList() {
        return roleMapper.selectList(null);
    }

    @Override
    public int insertRole(Role role) {
        return roleMapper.insert(role);
    }

    /**
     * 根据用户id查询所有权限，并且查询出当前用户拥有哪些权限
     * @param uid
     * @return
     */
    @Override
    public List<Role> roleListByUi(Integer uid) {
        return roleMapper.queryListByUid(uid);
    }
}
