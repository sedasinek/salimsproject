package com.yelloware.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.yelloware.entity.Role;
import com.yelloware.repository.RoleRepository;

import javax.persistence.EntityNotFoundException;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class RoleController {

    
	@Autowired
    private  RoleRepository repository;


    @GetMapping("/role/name/{roleName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Role>  saveRole(@PathVariable String roleName){
       System.out.println("saveRole() - start: book = {}"+roleName);
        List<Role>  savedRole = repository.findByName(roleName);
        //System.out("saveRole() - end: savedRole = {}", savedRole.get;
        return savedRole;
    }
    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    public Collection<Role> getAllRole() {
       System.out.println("getAllRoles() - start");
        Collection<Role> collection = repository.findAll();
       System.out.println("getAllRoles() - end");
        return collection;
    }
    

    @GetMapping("/role/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role getRoleById(@PathVariable Long id) {
       System.out.println("getRoleById() - start: id = {}"+id);
        Role receivedRole = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id = Not found"));
       System.out.println("getRoleById() - end: book = {}"+receivedRole.getId());
        return receivedRole;
    }

    @GetMapping(value = "/roles", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public Collection<Role> findRoleByName(@RequestParam(value = "name") String name) {
       System.out.println("findRoleByName() - start: name = {}"+name);
        Collection<Role> collection = repository.findByName(name);
       System.out.println("findRoleByName() - end: collection = {}"+collection);
        return collection;
    }

  /* @PutMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Role refreshRole(@PathVariable("id") long id, @RequestBody Role role) {
       System.out.println("refreshRole() - start: id = {}, book = {}" +id+" " +role);
      Role updatedRole= repository.findById(id)
                .map(entity -> {
                    entity.setName(role.getId());
                    entity.setDescription(role.getDescription());
                    entity.setTags(role.getTags());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new EntityNotFoundException("Role with id = Not found"));
       System.out.println("refreshRole() - end: updatedRole = {}"+updatedRole);
        return updatedRole;
    }
*/
    @DeleteMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoleById(@PathVariable Long id) {
       System.out.println("removeRoleById() - start: id = {}" +id);
        repository.deleteById(id);
       System.out.println("removeRoleById() - end: id = {}"+id);
    }

    @DeleteMapping("/roles")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllRoles() {
       System.out.println("removeAllRoles() - start");
        repository.deleteAll();
      System.out.println("removeAllRoles() - end");
    }
}

