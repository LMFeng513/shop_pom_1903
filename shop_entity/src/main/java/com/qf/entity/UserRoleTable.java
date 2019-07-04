package com.qf.entity;

  /*
    @author: LMFeng
    @date: 2019-07-03 19:14
    @desc:
  */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleTable implements Serializable {

    private Integer uid;
    private Integer rid;

}
