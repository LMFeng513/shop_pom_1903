package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-03 11:26
    @desc:
  */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/tologin")
    public String tologin(){

        return "login";

    }

    @RequestMapping("/login")
    public String login(){
        return "index";
    }


}
