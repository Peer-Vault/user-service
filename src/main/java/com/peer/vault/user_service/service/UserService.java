package com.peer.vault.user_service.service;

import com.peer.vault.user_service.domain.UserCredential;
import com.peer.vault.user_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserCredentialRepository userCredentialRepository;


    public Optional<UserCredential> getUserById(Long id) {
        return userCredentialRepository.findById(id);
    }
}
