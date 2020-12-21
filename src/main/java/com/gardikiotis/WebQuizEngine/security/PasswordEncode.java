package com.gardikiotis.WebQuizEngine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


public class PasswordEncode {
    @Autowired
   private static PasswordEncoder encoder;

    public static String encodePassword(String s){
        return encoder.encode(s);
    }
}
