package com.jwt.model;

import com.jwt.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class CustomUserDetails extends User implements UserDetails {
	public CustomUserDetails() {
	}

	public CustomUserDetails(final User user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		ArrayList list = new ArrayList();
//		list.add(new SimpleGrantedAuthority(getRoleId().getRole()));
		return list;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
//        return super.getEmailVerified();
		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		CustomUserDetails that = (CustomUserDetails) obj;
		return Objects.equals(getId(), that.getId());
	}
}
