package com.jwt.services;

import com.jwt.entity.User;
import com.jwt.model.UserDto;
import com.jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;


    public Object createUser(UserDto userDto) {
     User user = new User();

     user.setName(userDto.getUsername());
     user.setEmail(userDto.getEmail());
     user.setPassword(passwordEncoder.encode(userDto.getPassword()));

    return userRepository.save(user);

    }
}
