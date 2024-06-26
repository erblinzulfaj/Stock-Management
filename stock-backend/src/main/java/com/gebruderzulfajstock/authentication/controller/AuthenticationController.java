package com.gebruderzulfajstock.authentication.controller;

import com.gebruderzulfajstock.authentication.authModel.AuthenticationRequest;
import com.gebruderzulfajstock.authentication.authModel.AuthenticationResponse;
import com.gebruderzulfajstock.authentication.authModel.LoginRequest;
import com.gebruderzulfajstock.authentication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}