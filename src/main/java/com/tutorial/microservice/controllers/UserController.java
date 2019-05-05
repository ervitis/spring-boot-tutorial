package com.tutorial.microservice.controllers;

import com.tutorial.microservice.exceptions.UserNotFoundException;
import com.tutorial.microservice.models.UserData;
import com.tutorial.microservice.repositories.UserList;
import org.eclipse.jetty.server.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserList userList;

    @GetMapping(path = "/users")
    public List getAllUsers() {
        return userList.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<UserData> getUser(@PathVariable int id) {
        UserData userData = userList.findOne(id);

        if (userData == null) {
            throw new UserNotFoundException(String.format("%s", id));
        }

        return ResponseEntity.ok(userData);
    }

    @PostMapping("/users")
    public ResponseEntity<UserData> createUser(@Valid @RequestBody UserData userData) {
        UserData savedUser = userList.save(userData);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        if (!userList.delete(id)) {
            throw new UserNotFoundException(String.format("%s", id));
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
