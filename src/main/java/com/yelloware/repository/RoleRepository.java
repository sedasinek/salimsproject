package com.yelloware.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.yelloware.entity.Role;

import java.util.List;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);

}
