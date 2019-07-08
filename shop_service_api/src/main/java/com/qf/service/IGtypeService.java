package com.qf.service;

  /*
    @author: LMFeng
    @date: 2019-07-08 8:33
    @desc:
  */


import com.qf.entity.Gtype;

import java.util.List;

public interface IGtypeService {

    List<Gtype> toList();

    int addGtype(Gtype gtype);

    boolean deleteGtypeById(Integer id);

    Gtype listById(Integer id);

    boolean update(Gtype gtype);

    List<Gtype> getSecondType();
}
