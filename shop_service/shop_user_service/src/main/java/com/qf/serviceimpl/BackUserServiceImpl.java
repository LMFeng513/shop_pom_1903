package com.qf.serviceimpl;

  /*
    @author: LMFeng
    @date: 2019-07-02 19:51
    @desc:
  */


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.BackUserMapper;
import com.qf.dao.UserRoleMapper;
import com.qf.entity.BackUser;
import com.qf.entity.UserRoleTable;
import com.qf.service.IBackUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Queue;

@Service
public class BackUserServiceImpl implements IBackUserService {

    @Autowired
    private BackUserMapper backUserMapper;
@Autowired
private UserRoleMapper userRoleMapper;
    @Override
    public List<BackUser> queryAll() {
        return backUserMapper.selectList(null);
    }

    @Override
    public int insertUser(BackUser backUser) {
        return backUserMapper.insert(backUser);
    }




    @Override
    @Transactional
    public int updateUserRoles(Integer uid, Integer[] rid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid",uid);
        userRoleMapper.delete(queryWrapper);

        for (Integer roleid :rid){
            UserRoleTable userRoleTable =new UserRoleTable(uid,roleid);
            userRoleMapper.insert(userRoleTable);
        }

        return 1;
    }

//    @Override
//    public BackUser login(String username, String password) {
//       BackUser backUser=backUserMapper.queryByUserName(username);
//
//       if (backUser !=null && backUser.getPassword().equals(password)){
//           return backUser;
//       }
//
//
//        return null;
//    }

    /*securit
    * 提供的登录认证方法*/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        BackUser backUser =backUserMapper.queryByUserName(username);

        if (backUser==null){
            throw new UsernameNotFoundException("该用户不存在");
        }
        return backUser;
    }
}
