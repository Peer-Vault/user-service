package com.peer.vault.user_service.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private String firstName;

    private String lastName;

    private String gender;

    @Email(regexp = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",flags = Pattern.Flag.CASE_INSENSITIVE)
    private String email;

    private String password;

    private String phoneNumber;

    private LocalDateTime dateOfBirth;

    private String address;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    private LocalDateTime accountCreatedAt;

    private String profilePictureUrl;

    private String bio;




}
