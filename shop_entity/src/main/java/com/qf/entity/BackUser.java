package com.qf.entity;

  /*
    @author: LMFeng
    @date: 2019-07-02 19:28
    @desc:
  */


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackUser implements Serializable,UserDetails{
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer sex;
    private Date createtime =new Date();
    private Integer status;

    @TableField(exist = false)
    private List<Role>roles;
    @TableField(exist = false)
    private List<Power>powers;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      List<GrantedAuthority>authorities=new ArrayList<>();
        if (powers!=null&& powers.size()>0){
             for (Power power:powers){
                 if (power.getPowerpath()!=null&& !power.getPowerpath().equals("")){
                     authorities.add(new SimpleGrantedAuthority(power.getPowerpath()));

                 }
             }
        }


        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
