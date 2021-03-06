package com.pagantis.demo.controller;

import com.pagantis.demo.entity.Role;
import com.pagantis.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

//    Initial Roles set
    @GetMapping("/api/fillRoles")
    public String fill(){
        Optional<Role> rol = roleService.findByRole("ADMIN");
        Boolean ga = rol.isPresent();
        if (!roleService.findByRole("ADMIN").isPresent()){
            roleService.createRole(new Role("ADMIN"));
        }

        if (!roleService.findByRole("USER").isPresent()){
            roleService.createRole(new Role("USER"));
        }

        if (!roleService.findByRole("TEST").isPresent()){
            roleService.createRole(new Role("TEST"));
        }

        return "The roles have been initialized";
    }
}
