package com.ziggs.ziggs_backend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure-endpoint")
    public String secureEndpoint(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return "Error: Missing or invalid token";
            }
            String idToken = authorizationHeader.substring(7);

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            String uid = decodedToken.getUid();
            String role = (String) decodedToken.getClaims().get("role");

            return "Token is valid for user with UID: " + uid + " and role: " + role;
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
