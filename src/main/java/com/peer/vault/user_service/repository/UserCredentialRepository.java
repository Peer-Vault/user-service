package com.peer.vault.user_service.repository;

import com.peer.vault.user_service.domain.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Long> {

    Optional<UserCredential> findByEmail(String email);
}
