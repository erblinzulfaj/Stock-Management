package com.gebruderzulfajstock.authentication.service;
import com.gebruderzulfajstock.authentication.authModel.AuthenticationRequest;
import com.gebruderzulfajstock.authentication.authModel.AuthenticationResponse;
import com.gebruderzulfajstock.authentication.authModel.LoginRequest;
import com.gebruderzulfajstock.models.User;
import com.gebruderzulfajstock.models.enums.Role;
import com.gebruderzulfajstock.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(LoginRequest request) {
        User user = User.builder()
                .userName(request.getEmail())
                .password(request.getPassword())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userRepository.findByUserName(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}