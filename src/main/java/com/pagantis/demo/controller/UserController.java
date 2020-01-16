package com.pagantis.demo.controller;

import com.pagantis.demo.entity.User;
import com.pagantis.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

//    Initial Roles set
    @GetMapping("/api/fillUsers")
    public String fill(){
        Optional<User> admin = userService.findByEmail("admin@gmail.com");
        Optional<User> tUserFrom = userService.findByEmail("user1@gmail.com");
        Optional<User> tUserTo = userService.findByEmail("user2@gmail.com");
        if (!admin.isPresent()){
            userService.createUser(new User("admin@gmail.com","123456"),"ADMIN");
        }
        if (!tUserFrom.isPresent()){
            userService.createUser(new User("user1@gmail.com","123456"),"USER");
        }
        if (!tUserTo.isPresent()){
            userService.createUser(new User("user2@gmail.com","123456"),"USER");
        }
        return "The users have been initialized";
    }

    @GetMapping("/api/users")
    public List<User> retrieveUsers(){
        return userService.findAll();
    }

    @GetMapping("/api/users/{id}")
    public User retrieveUserByEmail(@PathVariable String email){
        return userService.findByEmail(email).get();
    }

    @PostMapping("/api/users")
    public ResponseEntity<?> createUser(@RequestBody User user){
        if (userService.findByEmail(user.getEmail()).isPresent()){
            return new ResponseEntity<>("User already exist", HttpStatus.CONFLICT);
        }else {
            User create = userService.createUser(user, "USER");
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(create.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
    }

}
