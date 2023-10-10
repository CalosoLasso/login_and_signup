package com.carlos.loging_and_sign.service;



import com.carlos.loging_and_sign.persistence.repository.UserAppRepository;
import com.carlos.loging_and_sign.persistence.entiy.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAppService {
    private final UserAppRepository userAppRepository;


    @Autowired
    public UserAppService(UserAppRepository userAppRepository) {
        this.userAppRepository = userAppRepository;
    }

    public void saveNewUser(UserEntity user){
        this.userAppRepository.save(user);

    }

    public boolean existByEmail(String email){
        return  this.userAppRepository.existByEmail(email);
    }

    public UserEntity findByEmail(String email){
        return  this.userAppRepository.findByEmail(email);
    }


    public boolean existByUsername(String username){
        return this.userAppRepository.existByUsername(username);
    }








}
