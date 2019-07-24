package com.qf.util;

  /*
    @author: LMFeng
    @date: 2019-07-06 9:59
    @desc:
  */

import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Component
public class PerssionHandler {

    /*进行权限热证*/
    public boolean hasPerssion(HttpServletRequest request, Authentication authentication){
        Object principal = authentication.getPrincipal();
        AntPathMatcher antPathMatcher=new AntPathMatcher();
        if (principal instanceof UserDetails){
            String requestURI = request.getRequestURI();
            UserDetails userDetails= (UserDetails) principal;

            Collection<? extends GrantedAuthority>authorities=userDetails.getAuthorities();

            for (GrantedAuthority authority:authorities){
                if (antPathMatcher.match(requestURI,authority.getAuthority())){
                    return true;
                }
            }

        }
        return false;
    }
}
