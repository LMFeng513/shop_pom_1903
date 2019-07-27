package com.qf.entity;

  /*
    @author: LMFeng
    @date: 2019-07-27 9:02
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
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopCart implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private Integer gid;
    private Integer gnumber;
    private BigDecimal sprice;
    private Date createtime;

    @TableField(exist = false)
    private Goods goods;
}
