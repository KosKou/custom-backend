package com.pagantis.demo.service;

import com.pagantis.demo.entity.Role;
import com.pagantis.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
private RoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Optional<Role> findById(String id){
        return roleRepository.findById(id);
    }

    public Optional<Role> findByRole(String role){
        return roleRepository.findByRole(role);
    }

    public boolean isRoleExist(String id){
        return roleRepository.existsById(id);
    }

    //Update & Save
    public Role createRole(Role role){
        role.setState("ACTIVE");
        return roleRepository.save(role);
    }

    public void deleteRole(String id){
        Role role = findById(id).get();
        role.setState("INACTIVE");
        roleRepository.deleteById(id);
    }
}
