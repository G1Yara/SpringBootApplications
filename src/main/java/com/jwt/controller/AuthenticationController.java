package com.jwt.controller;

import com.jwt.model.CustomUserDetails;
import com.jwt.model.LoginRequest;
import com.jwt.model.LoginResponse;
import com.jwt.security.JwtAuthenticationEntryPoint;
import com.jwt.services.AuthenticationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthenticationController {

    private static final Logger logger = Logger.getLogger(JwtAuthenticationEntryPoint.class);
    @Autowired
    private AuthenticationService authService;

    /**
     * Entry point for the user log in. Return the jwt auth token and the refresh token
     */
    @PostMapping("/v1/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authService.authenticateUser1(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        logger.info("Logged in User returned [API]: " + customUserDetails.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = authService.generateToken(customUserDetails);
        Long expiry = authService.getJwtTokenExpiry();
        LoginResponse loginResponse = new LoginResponse(jwtToken,expiry);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK) ;
    }
}
