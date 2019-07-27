package com.qf.entity;

  /*
    @author: LMFeng
    @date: 2019-07-27 9:15
    @desc:
  */


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetils implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String orderid;
    private Integer gid;
    private String gname;
    private BigDecimal gprice;
    private String gimage;
    private Integer gnumber;
    private BigDecimal sprice;
}
