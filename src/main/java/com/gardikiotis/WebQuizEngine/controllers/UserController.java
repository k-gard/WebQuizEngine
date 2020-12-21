package com.gardikiotis.WebQuizEngine.controllers;

import com.gardikiotis.WebQuizEngine.models.AppUser;
import com.gardikiotis.WebQuizEngine.models.CustomResponse;
import com.gardikiotis.WebQuizEngine.models.Error;
import com.gardikiotis.WebQuizEngine.HelperClass;
import com.gardikiotis.WebQuizEngine.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserService service;

    @PostMapping (path = "/api/register" , consumes = "application/json")
    public ResponseEntity registerUser(@Valid @RequestBody AppUser appUser) throws IOException {
        HelperClass.getInstance().log(appUser.toString());
        System.out.println(appUser.getPassword());
        System.out.println(encoder.toString());
        if (service.findUserByEmail(appUser.getEmail()) == null){
       //     appUser.setPassword(PasswordEncode.encodePassword(appUser.getPassword()));
            AppUser user = new AppUser(appUser.getEmail(),encoder.encode(appUser.getPassword()));
        service.registerUser(user);

        return  ResponseEntity.ok(user);
        }
        if (service.findUserByEmail(appUser.getEmail()) != null){
            return new ResponseEntity<>(new Error("400","This email is already registered"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping(path = "/api/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
            service.deleteUserById(id);
            return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/api/users")
    public List<AppUser> getAllUsers(){
      return  service.getAllUsers();
    }

    @GetMapping(path = "/api/login")
    public CustomResponse login(){
        return  new CustomResponse("authenticated");
    }

    @GetMapping(path = "/api/users/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){

        if (service.findUserByEmail(email) != null){
           return  ResponseEntity.ok("OK");
        };
        return ResponseEntity.notFound().build();
    }
}
