package com.projectmanagement.projectmanagement.service;

import com.projectmanagement.projectmanagement.domain.User;
import com.projectmanagement.projectmanagement.dto.RegisterRequest;
import com.projectmanagement.projectmanagement.repository.UserRepository;
import com.projectmanagement.projectmanagement.util.CustomPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final CustomPasswordEncoder customPasswordEncoder;
    private final UserRepository userRepository;

    public boolean register(RegisterRequest request) {
        User newUser = new User();
        PasswordEncoder passwordEncoder = customPasswordEncoder.getPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(request.getPassword());

        newUser.setUsername(request.getUsername());
        newUser.setPassword(encryptedPassword);

        try {
            this.userRepository.save(newUser);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
