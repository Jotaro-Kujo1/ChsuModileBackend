package com.chsu.chsumodilebackend.controllers;

import com.chsu.chsumodilebackend.models.User;
import com.chsu.chsumodilebackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUser")
    public ResponseEntity<User> getUser(@RequestParam String login, @RequestParam String password){
        if(userService.checkUserValid(login,password)){
            return ResponseEntity.ok(userService.findByLogin(login));
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        if(userService.checkUserExist(user)){
            User savedUser = userService.saveUser(user);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path(savedUser.getLogin())
                    .buildAndExpand(savedUser.getLogin())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else {
            return ResponseEntity.status(404).build();
        }
    }
}
