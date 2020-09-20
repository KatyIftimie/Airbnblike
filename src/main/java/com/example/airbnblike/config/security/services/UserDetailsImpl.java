package com.example.airbnblike.config.security.services;

import com.example.airbnblike.auth.model.User;
import com.example.airbnblike.auth.model.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String email;

	@JsonIgnore
	private String password;

	private UserType type;

	public UserType getType() {
		return type;
	}

	public UserDetailsImpl(Long id, String email, String password,
						   UserType type) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.type = type;
	}

	public static UserDetailsImpl build(User user) {

		return new UserDetailsImpl(
				user.getId(),
				user.getEmail(),
				user.getPassword(), 
				user.getType());
	}



	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return null;
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
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public String toString() {
		return "UserDetailsImpl{" +
				"id=" + id +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", type=" + type +
				'}';
	}
}
