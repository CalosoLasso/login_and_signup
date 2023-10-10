package com.carlos.loging_and_sign.service;

import com.carlos.loging_and_sign.persistence.entiy.UserEntity;
import com.carlos.loging_and_sign.persistence.repository.UserAppRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private final UserAppRepository userAppRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity =  this.userAppRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles("USER")
                .accountLocked(userEntity.getLocked())
                .disabled(userEntity.getDisable())
                .build();

    }
}
