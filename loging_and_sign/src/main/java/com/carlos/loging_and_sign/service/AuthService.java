package com.carlos.loging_and_sign.service;

import com.carlos.loging_and_sign.persistence.entiy.RoleEntity;
import com.carlos.loging_and_sign.persistence.entiy.UserEntity;
import com.carlos.loging_and_sign.service.dto.LoginDto;
import com.carlos.loging_and_sign.service.dto.UserRegisterDto;
import com.carlos.loging_and_sign.web.config.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserAppService userAppService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public ResponseEntity login(LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);

        String jwt = this.jwtUtil.create(loginDto.getUsername());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();

    }

    public ResponseEntity register(UserRegisterDto user) {
        UserEntity userEntity = UserEntity.builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .userEmail(user.getEmail())
                .disable(false)
                .locked(false)
                .role(RoleEntity.valueOf("USER"))
                .build();


        if (userAppService.existByEmail(user.getEmail()) || userAppService.existByUsername(user.getUsername())){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }


        userAppService.saveNewUser(userEntity);
        String jwt = this.jwtUtil.create(userEntity.getUsername());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();

    }
}
