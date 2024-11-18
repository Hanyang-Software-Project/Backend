package com.ziggs.ziggs_backend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.ziggs.ziggs_backend.entity.User;
import com.ziggs.ziggs_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;


@RestController
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/generate-token")
    public Map<String, Object> generateCustomToken(@RequestParam Long userId) {
        Map<String, Object> response = new HashMap<>();
        try {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("Utilisateur introuvable avec l'ID : " + userId));

            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole().toString());

            String customToken = FirebaseAuth.getInstance().createCustomToken(user.getUserId().toString(), claims);

            response.put("success", true);
            response.put("token", customToken);
            return response;
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return response;
        }
    }
}

