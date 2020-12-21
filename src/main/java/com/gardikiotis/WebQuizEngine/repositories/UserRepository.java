package com.gardikiotis.WebQuizEngine.repositories;

import com.gardikiotis.WebQuizEngine.models.AppUser;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<AppUser,Integer> {
   // AppUser findByUsername(String s);

    AppUser findByEmail(String s);
    //  AppUser findByUsername(String s);
}
