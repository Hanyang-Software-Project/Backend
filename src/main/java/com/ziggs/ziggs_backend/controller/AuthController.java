package com.ziggs.ziggs_backend.controller;
import com.google.firebase.auth.FirebaseAuth;
import com.ziggs.ziggs_backend.dto.LoginRequestDTO;
import com.ziggs.ziggs_backend.entity.User;
import com.ziggs.ziggs_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            boolean isValidUser = userService.verifyUserCredentials(
                    loginRequest.getEmail(), loginRequest.getPassword());

            if (isValidUser) {
                User user = userService.findByEmail(loginRequest.getEmail());
                Map<String, Object> claims = new HashMap<>();
                claims.put("role", user.getRole().toString());

                String customToken = FirebaseAuth.getInstance()
                        .createCustomToken(user.getUserId().toString(), claims);

                customToken = "{\"token\": \"" + customToken + "\"}";

                return ResponseEntity.ok(customToken);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}
