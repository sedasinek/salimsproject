package com.yelloware.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yelloware.entity.User;



public interface UserRepository extends  JpaRepository<User, Integer>{
 
     
    public User findByUsername(String username);
}
