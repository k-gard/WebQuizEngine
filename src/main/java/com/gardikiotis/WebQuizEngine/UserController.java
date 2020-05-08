package com.gardikiotis.WebQuizEngine;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService service;

    @PostMapping (path = "/api/register" , consumes = "application/json")
    public ResponseEntity registerUser(@Valid @RequestBody CustomUser customUser) throws IOException {
        HelperClass.getInstance().log(customUser.toString());
        System.out.println(customUser.getPassword());
        System.out.println(encoder.toString());
        if (service.findUserByEmail(customUser.getEmail()) == null){
       //     customUser.setPassword(PasswordEncode.encodePassword(customUser.getPassword()));
            CustomUser user = new CustomUser(customUser.getEmail(),encoder.encode(customUser.getPassword()));
        service.registerUser(user);

        return  ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/api/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
            service.deleteUserById(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/api/users")
    public List<CustomUser> getAllUsers(){
      return  service.getAllUsers();
    }


}
