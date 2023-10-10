package com.carlos.loging_and_sign.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.carlos.loging_and_sign.service.dto.UserRegisterDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class JwtUtil {
    private static String SECRET_KEY = "B9F1498B64CF22D91128C212C8A31";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET_KEY);


    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("pizzeria-pepe-INC")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

    public boolean isValid(String token){
        try {JWT.require(ALGORITHM)
                .build()
                .verify(token);
            return true;

        }catch (JWTVerificationException e){
            return false;
        }
    }

    public String getUsername(String token){
        return JWT.require(ALGORITHM)
                .build()
                .verify(token)
                .getSubject();
    }



}
