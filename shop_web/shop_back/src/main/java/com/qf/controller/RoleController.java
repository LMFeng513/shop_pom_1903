package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-03 19:47
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Role;
import com.qf.service.IRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")

public class RoleController {

    @Reference
    private IRoleService roleService;



    /**
     * 角色列表
     * @return
     */
    @RequestMapping("/list")
    public String roleList(Model model){
        List<Role>roles=roleService.roleList();
        model.addAttribute("roles",roles);
        return "rolelist";


    }

    /**
     * 通过ajax的方式查询所有角色
     * @return
     */
    @RequestMapping("/listajax")
    @ResponseBody
    public List<Role>roleListAjax(Integer uid){

        List<Role>roles=roleService.roleListByUi(uid);
        return roles;
    }

    /*添加角色*/

    @RequestMapping("/insert")
    public String roleInsert(Role role){
        roleService.insertRole(role);
        return  "redirect:/role/list";

    }

}




