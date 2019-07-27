package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/*
    @author: LMFeng
    @date: 2019-07-27 9:15
    @desc:
  */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer uid;
    private String person;
    private String addtess;
    private String phone;
    private Integer isdefault =0;
    private Date ceatetime =new Date();


}
