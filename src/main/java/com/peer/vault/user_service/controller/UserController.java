package com.peer.vault.user_service.controller;

import com.peer.vault.user_service.domain.UserCredential;
import com.peer.vault.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/userinfo")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUserById")
    public ResponseEntity<Optional<UserCredential>> getUserById(@RequestParam("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }

}
