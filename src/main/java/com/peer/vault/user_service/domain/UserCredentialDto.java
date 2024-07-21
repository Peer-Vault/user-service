package com.peer.vault.user_service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentialDto {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String gender;

    private String email;

    private String phoneNumber;

    private LocalDateTime dateOfBirth;

    private String address;

    private List<String> roles;

    private LocalDateTime accountCreatedAt;

    private String profilePictureUrl;

    private String bio;
}
