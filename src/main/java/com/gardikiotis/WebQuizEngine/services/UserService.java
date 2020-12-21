package com.gardikiotis.WebQuizEngine.services;

import com.gardikiotis.WebQuizEngine.models.AppUser;
import com.gardikiotis.WebQuizEngine.repositories.UserRepository;
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

    public void registerUser(AppUser appUser){
       repository.save(appUser);
    }


    public AppUser findUserById(int id) {
       return repository.findById(id).get();
    }

    public AppUser findUserByEmail(String email) {
        Collection<AppUser> users = (Collection<AppUser>) repository.findAll();
        try{
        return users.stream().filter(x -> x.getEmail().equals(email)).findFirst().get();}
        catch (NoSuchElementException e){
            return null;
        }
    }

    public void deleteUserById(int id) {
        repository.deleteById(id);
    }

    public List<AppUser> getAllUsers() {
        return (ArrayList<AppUser>) repository.findAll();
    }

    public void updateUser(AppUser user) {
        repository.save(user);
    }
}
