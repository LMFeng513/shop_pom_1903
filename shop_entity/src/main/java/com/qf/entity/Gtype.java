package com.qf.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gtype implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer pid = -1;
    private String type;
    private Integer status;

    @TableField(exist = false)
    private String ptype;

    @TableField(exist = false)
    private boolean checked;
}
