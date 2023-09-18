package com.volbog.ranobe.service;

import com.volbog.ranobe.entity.User;
import com.volbog.ranobe.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HttpUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public HttpUserDetailsService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String username) {
    final User user = userRepository.findByEmailIgnoreCase(username);
    if (user == null) {
      log.warn("user not found: {}", username);
      throw new UsernameNotFoundException("User " + username + " not found");
    }
    final List<SimpleGrantedAuthority> roles = Collections.singletonList(
        new SimpleGrantedAuthority("ROLE_USER"));
    return new org.springframework.security.core.userdetails.User(username, user.getPassword(),
        roles);
  }

}
