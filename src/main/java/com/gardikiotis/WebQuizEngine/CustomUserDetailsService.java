package com.gardikiotis.WebQuizEngine;

//import com.sun.security.auth.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;



    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        CustomUser user = this.userRepository.findByEmail(s);

        return user;
    }


    @ExceptionHandler({UsernameNotFoundException.class})
    public ResponseEntity handleUsernameNotFoundException(){
        return new ResponseEntity<>(new Error("Not Found","CustomUser does not exist"),null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
