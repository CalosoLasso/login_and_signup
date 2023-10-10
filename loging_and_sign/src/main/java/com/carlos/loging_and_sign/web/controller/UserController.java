package com.carlos.loging_and_sign.web.controller;


import com.carlos.loging_and_sign.service.AuthService;
import com.carlos.loging_and_sign.service.dto.LoginDto;
import com.carlos.loging_and_sign.service.dto.UserRegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final AuthService authService;



    @PostMapping("/signup")
    public ResponseEntity newUser(@RequestBody UserRegisterDto user){
        return authService.register(user);

    }



    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        return authService.login(loginDto);

    }



}
