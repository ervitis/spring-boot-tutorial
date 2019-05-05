package com.tutorial.microservice.controllers;

import com.tutorial.microservice.models.UserData;
import com.tutorial.microservice.repositories.UserList;
import org.eclipse.jetty.server.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(userData);
    }

    @PostMapping("/users")
    public UserData createUser(@Valid @RequestBody UserData userData) {
        return userList.save(userData);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        if (userList.delete(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
