package com.qf.entity;

  /*
    @author: LMFeng
    @date: 2019-07-18 18:54
    @desc:
  */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email implements Serializable {

    private String to;
    private String subject;
    private String content;


}
