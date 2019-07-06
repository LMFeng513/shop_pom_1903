package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-03 11:26
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.BackUser;
import com.qf.service.IBackUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loginUser")
public class LoginController {
    @Reference
    private IBackUserService userService;

    @RequestMapping("/tologin")
    public String tologin(){

        return "login";

    }

    @RequestMapping("/nopersson")
    public String noPerssion(){
        return "noperssion";
    }

  /*  @RequestMapping("/login")
    public String login(String username, String password, Model model){
        BackUser user= userService.login(username,password);
        if(user!=null){
            model.addAttribute("loginUser",user);
            return "index";
        }

        return "redirect:/tologin?error=1";
    }*/


}
