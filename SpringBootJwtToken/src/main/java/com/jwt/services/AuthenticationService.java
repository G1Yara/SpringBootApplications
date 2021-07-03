package com.jwt.services;


import com.jwt.model.CustomUserDetails;
import com.jwt.model.LoginRequest;
import com.jwt.security.JwtTokenProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private static final Logger logger = Logger.getLogger(AuthenticationService.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Authenticate user and log them in given a loginRequest
     */
    public Authentication authenticateUser1(LoginRequest loginRequest) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail().toLowerCase(),
                loginRequest.getPassword()));
    }
    /**
     * Generates a JWT token for the validated client
     */
    public String generateToken(CustomUserDetails customUserDetails) {
        return tokenProvider.generateToken(customUserDetails);
    }
    public Long getJwtTokenExpiry(){
        return tokenProvider.getExpiryDuration();
    }

}
