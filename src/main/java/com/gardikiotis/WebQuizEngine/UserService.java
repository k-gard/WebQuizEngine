package com.gardikiotis.WebQuizEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void registerUser(CustomUser customUser){
       repository.save(customUser);
    }


    public CustomUser findUserById(int id) {
       return repository.findById(id).get();
    }

    public CustomUser findUserByEmail(String email) {
        Collection<CustomUser> users = (Collection<CustomUser>) repository.findAll();
        try{
        return users.stream().filter(x -> x.getEmail().equals(email)).findFirst().get();}
        catch (NoSuchElementException e){
            return null;
        }
    }

    public void deleteUserById(int id) {
        repository.deleteById(id);
    }

    public List<CustomUser> getAllUsers() {
        return (ArrayList<CustomUser>) repository.findAll();
    }

    public void updateUser(CustomUser user) {
        repository.save(user);
    }
}
