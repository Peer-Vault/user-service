package com.peer.vault.user_service.controller;


import com.peer.vault.user_service.config.CustomUserDetails;
import com.peer.vault.user_service.domain.AuthRequest;
import com.peer.vault.user_service.domain.UserCredential;
import com.peer.vault.user_service.domain.UserCredentialDto;
import com.peer.vault.user_service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserCredential> addNewUser(@RequestBody UserCredential user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.OK).body(service.generateToken(authRequest.getEmail()));
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserCredentialDto> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            UserCredential userCredential = customUserDetails.getUserCredential();

            return ResponseEntity.ok(service.getCurrentUser(userCredential));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }


}
