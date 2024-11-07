package com.ziggs.ziggs_backend.controller;
import com.ziggs.ziggs_backend.dto.LoginRequestDTO;
import com.ziggs.ziggs_backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequest) {
        boolean isValidUser = userService.verifyUserCredentials(
                loginRequest.getEmail(), loginRequest.getPassword());

        if (isValidUser) {
            // Return a success message, or ideally, a JWT or session token for authentication
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
