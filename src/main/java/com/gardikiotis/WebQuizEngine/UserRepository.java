package com.gardikiotis.WebQuizEngine;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<CustomUser,Integer> {
   // CustomUser findByUsername(String s);

    CustomUser findByEmail(String s);
    //  CustomUser findByUsername(String s);
}
