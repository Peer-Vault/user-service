package com.peer.vault.user_service.service;

import com.peer.vault.user_service.domain.UserCredential;
import com.peer.vault.user_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private KafkaProducerService kafkaProducerService;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        credential.setAccountCreatedAt(LocalDateTime.now());
        repository.save(credential);
//        kafkaProducerService.sendNotificationRegistration(credential.getEmail());
        return "user added to the system";
    }

    public String generateToken(String email) {
        UserCredential user = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return jwtService.generateToken(email, user.getRoles());
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


}
