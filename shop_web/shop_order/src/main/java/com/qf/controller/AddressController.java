package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-27 15:48
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.aop.IsLogin;
import com.qf.entity.Address;
import com.qf.entity.User;
import com.qf.service.IAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/address")
public class AddressController {


    @Reference
    private IAddressService addressService;

    @IsLogin(mustLogin = true)
    @RequestMapping("/insert")
    public String insert(Address address, User user){

        address.setUid(user.getId());
        addressService.insertAddress(address);

        return "redirect:/order/edit";
    }


}
