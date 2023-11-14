package com.projectmanagement.projectmanagement.web;

import com.projectmanagement.projectmanagement.dto.RegisterRequest;
import com.projectmanagement.projectmanagement.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("signup")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        boolean userRegistered = registerService.register(request);
        if (userRegistered) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
