package com.projectmanagement.projectmanagement.service;

import com.projectmanagement.projectmanagement.domain.User;
import com.projectmanagement.projectmanagement.repository.UserRepository;
import com.projectmanagement.projectmanagement.util.CustomPasswordEncoder;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomPasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userOpt = userRepo.findByUsername(username);
        return userOpt.orElseThrow(() -> new UsernameNotFoundException("Invalid credentials"));
    }
}
