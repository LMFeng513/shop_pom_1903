package com.qf.pass;

  /*
    @author: LMFeng
    @date: 2019-07-18 20:35
    @desc:
  */


import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptUtil {
    public static String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String hash,String password){
        return BCrypt.checkpw(password,hash);
    }
}
