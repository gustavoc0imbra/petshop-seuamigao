package org.uniara.usersapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.uniara.usersapi.DTOs.LoginUserDTO;
import org.uniara.usersapi.constant.Constant;
import org.uniara.usersapi.model.ResponseToken;
import org.uniara.usersapi.model.User;
import org.uniara.usersapi.security.JwtTokenProvider;
import org.uniara.usersapi.service.UserService;

import java.util.Optional;

@RestController
public class AuthController {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserService userService;

    @PostMapping(Constant.API_AUTH)
    public ResponseToken auth(@RequestBody LoginUserDTO user) {
        Optional<User> userFound = userService.findByEmail(user.getEmail());

        if(userFound.isEmpty() || !userFound.get().getPassword().equals(user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        String token = jwtTokenProvider.generateToken(user.getEmail());
        return new ResponseToken("Autenticado!", token);
    }

    @PostMapping(Constant.API_AUTH + "/validate")
    public boolean validate(@RequestHeader("Authorization") String token) {
        return jwtTokenProvider.validateToken(token.replace("Bearer ", ""));
    }
}
