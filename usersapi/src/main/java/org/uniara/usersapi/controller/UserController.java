package org.uniara.usersapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.uniara.usersapi.constant.Constant;
import org.uniara.usersapi.model.User;
import org.uniara.usersapi.security.JwtTokenProvider;
import org.uniara.usersapi.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping(Constant.API_USERS)
    public ResponseEntity<List<User>> findAll(@RequestHeader("Authorization") String token) {

        if (!jwtTokenProvider.validateToken(token.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping(Constant.API_USERS + "/{id}")
    public ResponseEntity<Optional<User>> findById(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {

        if (!jwtTokenProvider.validateToken(token.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping(Constant.API_USERS)
    public ResponseEntity<User> save(@RequestBody User user) {
        user.setStatus(true);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping(Constant.API_USERS)
    public ResponseEntity<User> update(@RequestHeader("Authorization") String token, @RequestBody User user) {

        if (!jwtTokenProvider.validateToken(token.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(userService.save(user));
    }

    @DeleteMapping(Constant.API_USERS + "/{id}")
    public ResponseEntity<User> delete(@RequestHeader("Authorization") String token, @PathVariable("id") Long id) {

        if (!jwtTokenProvider.validateToken(token.replace("Bearer ", ""))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
