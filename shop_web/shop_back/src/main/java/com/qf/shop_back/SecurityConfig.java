package com.qf.shop_back;

  /*
    @author: LMFeng
    @date: 2019-07-06 9:00
    @desc:
  */


import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.service.IBackUserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Reference
    private IBackUserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                    .loginPage("/tologin")
                    .loginProcessingUrl("/login")
                    .failureUrl("/tologin?error=1")
                    .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .authorizeRequests()
                    .mvcMatchers("/resources/**").permitAll()//静态资源放行

                     .mvcMatchers("/").authenticated()
                   // .anyRequest().access("@perssionHandler.hasPerssion(request,authentication)")
                    .anyRequest().authenticated()

                .and()
                .csrf().disable()
                //处理iframe请求，让security放行
                .headers().frameOptions().sameOrigin()
                .and()

               .exceptionHandling().accessDeniedPage("/noperssion");


    /*自定义身份认证方法*/



    }           /**/



}
