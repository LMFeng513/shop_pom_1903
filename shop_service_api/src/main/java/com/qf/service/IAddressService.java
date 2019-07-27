package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-27 9:49
    @desc:
  */

import com.qf.entity.Address;

import java.util.List;

public interface IAddressService {
    List<Address>queryByUid(Integer uid);

    int insertAddress(Address address);

    Address queryByAid(Integer aid);
}
