package com.qf.entity;

  /*
    @author: LMFeng
    @date: 2019-07-05 13:14
    @desc:
  */



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePowerTable implements Serializable {

    private Integer rid;
    private Integer pid;
}
