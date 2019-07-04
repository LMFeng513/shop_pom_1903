package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-03 11:17
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.BackUser;
import com.qf.service.IBackUserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/buser")
public class BackUserController {
    @Reference
    private IBackUserService backUserService;

    /*查询后台用户的列表数据*/
    @RequestMapping("/list")
    public String userList(Model model){
        //调用后台服务，查询列表
        List<BackUser>backUsers=backUserService.queryAll();
        model.addAttribute("users",backUsers);

        return "buserlist";

    }

    /*添加用户
    * @param backUser
    * */

    @RequestMapping("/insert")
    public String userAadd(BackUser backUser){
        backUserService.insertUser(backUser);

        return "redirect:/buser/list";
    }

    @RequestMapping("/updaterole")
    public String updateUserRole(Integer uid,Integer[]rid){
        backUserService.updateUserRoles(uid,rid);
        return "redirect:/buser/list";
    }

}
