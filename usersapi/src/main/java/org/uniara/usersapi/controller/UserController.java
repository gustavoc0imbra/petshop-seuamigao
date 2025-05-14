package org.uniara.usersapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.usersapi.constant.Constant;
import org.uniara.usersapi.model.User;
import org.uniara.usersapi.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(Constant.API_USERS)
    public ResponseEntity<List<User>> findAll(/*@RequestHeader("Authorization") String token*/) {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(Constant.API_USERS + "/{id}")
    public ResponseEntity<Optional<User>> findById(/*@RequestHeader("Authorization") String token,*/ @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping(Constant.API_USERS)
    public ResponseEntity<User> save(/*@RequestHeader("Authorization") String token,*/ @RequestBody User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping(Constant.API_USERS)
    public ResponseEntity<User> update(/*@RequestHeader("Authorization") String token,*/ @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping(Constant.API_USERS + "{id}")
    public ResponseEntity<User> delete(/*@RequestHeader("Authorization") String token,*/ @PathVariable("id") Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
