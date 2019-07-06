package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-06 11:04
    @desc:
  */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

        @RequestMapping("/list")
        public String list()
        {
          return "list";
        }
}
