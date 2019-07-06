package com.qf.entity;

  /*
    @author: LMFeng
    @date: 2019-07-06 10:54
    @desc:
  */


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String gname;
    private String ginfo;
    private String gimage;
    private BigDecimal gprice;
    private Integer tid;
    private Integer gsave;





}
