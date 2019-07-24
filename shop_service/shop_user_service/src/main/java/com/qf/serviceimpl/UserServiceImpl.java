package com.qf.serviceimpl;

  /*
    @author: LMFeng
    @date: 2019-07-18 20:23
    @desc:
  */


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.UserMapper;
import com.qf.entity.User;
import com.qf.pass.BCryptUtil;
import com.qf.service.IUserService;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int register(User user) {

        //判断用户名是否唯一
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("username",user.getUsername());
        User u =userMapper.selectOne(queryWrapper);

        if (u!=null){
            return -1;

        }

        QueryWrapper queryWrapper1 =new QueryWrapper();
        queryWrapper.eq("email",user.getEmail());
        User u1 =userMapper.selectOne(queryWrapper);

        if (u1!=null){
            return -2;
        }
        user.setPassword(BCryptUtil.hashPassword(user.getPassword()));
        return userMapper.insert(user);
    }

    @Override
    public User queryByUserName(String username) {
        QueryWrapper queryWrapper =new QueryWrapper();
        queryWrapper.eq("username",username);
        User user =userMapper.selectOne(queryWrapper);

        return user;
    }

    @Override
    public int updatePassword(String username, String password) {
        User user =queryByUserName(username);
        user.setPassword(BCryptUtil.hashPassword(password));

        return userMapper.updateById(user);
    }
}