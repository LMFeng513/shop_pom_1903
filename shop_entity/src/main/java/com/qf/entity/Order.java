package com.qf.entity;

  /*
    @author: LMFeng
    @date: 2019-07-27 9:15
    @desc:
  */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("orders")
public class Order implements Serializable {

    @TableId(type = IdType.INPUT)
    private String orderid;
    private String person;
    private String address;
    private String phone;
    private BigDecimal allprice;
    private Date createtime;
    private Integer status;//0 - 未支付 1 - 已支付/待发货  2 - 已发货 3 - 已收货

    @TableField(exist = false)
    private List<OrderDetils> orderDetils;
}
