package com.jwt.controller;

import com.jwt.model.UserDto;
import com.jwt.security.JwtAuthenticationEntryPoint;
import com.jwt.services.UserService;
import io.swagger.annotations.Api;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Api(tags = "User")
public class UserController {
    private static final Logger logger = Logger.getLogger(JwtAuthenticationEntryPoint.class);
    @Autowired
    private UserService userService;

    @PostMapping("/v1/register")
    public ResponseEntity<?> authenticateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }
}
