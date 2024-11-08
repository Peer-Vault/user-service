package com.peer.vault.user_service.service;

import com.peer.vault.user_service.domain.UserCredential;
import com.peer.vault.user_service.domain.UserCredentialDto;
import com.peer.vault.user_service.messaging.KafkaProducer;
import com.peer.vault.user_service.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtService jwtService;

    public UserCredential saveUser(UserCredential credential) {
        credential.setPassword(passwordEncoder.encode(credential.getPassword()));
        credential.setAccountCreatedAt(LocalDateTime.now());
        UserCredential savedUser = repository.save(credential);
        if (producer != null){
            producer.producerForRegistration(credential);
        }
//        kafkaProducerService.sendNotificationRegistration(credential.getEmail());
//        emailService.sendRegistrationSuccessEmail(credential);
        return savedUser;
    }

    public String generateToken(String email) {
        UserCredential user = repository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return jwtService.generateToken(email, user.getRoles());
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }


    public UserCredentialDto getCurrentUser(UserCredential userCredential) {

        UserCredentialDto userCredentialDto = new UserCredentialDto(
                userCredential.getId(),
                userCredential.getUsername(),
                userCredential.getFirstName(),
                userCredential.getLastName(),
                userCredential.getGender(),
                userCredential.getEmail(),
                userCredential.getPhoneNumber(),
                userCredential.getDateOfBirth(),
                userCredential.getAddress(),
                userCredential.getRoles(),
                userCredential.getAccountCreatedAt(),
                userCredential.getProfilePictureUrl(),
                userCredential.getBio()
        );

        return userCredentialDto;
    }
}
