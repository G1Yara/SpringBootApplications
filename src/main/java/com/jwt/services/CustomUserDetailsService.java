package com.jwt.services;


import com.jwt.entity.User;
import com.jwt.model.CustomUserDetails;
import com.jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User dbUser = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("Invalid user"));
		return getUserDetails(dbUser);
	}

	public UserDetails loadUserById(int id) {
		User dbUser = userRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("Couldn't find a matching id in the " + "database for " + id));
		return getUserDetails(dbUser);
	}
	public CustomUserDetails getUserDetails(User user) {
		CustomUserDetails customUserDetails = new CustomUserDetails();
		customUserDetails.setId(user.getId());
		customUserDetails.setEmail(user.getEmail());
		customUserDetails.setName(user.getName());
		customUserDetails.setPassword(user.getPassword());
		return customUserDetails;
	}
}
