package com.ziggs.ziggs_backend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import com.ziggs.ziggs_backend.dto.SecureDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/secure-endpoint")
    public ResponseEntity<Object> secureEndpoint(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                SecureDTO res = new SecureDTO("Error: Missing or invalid token", null, null);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
            }
            String idToken = authorizationHeader.substring(7);

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            String uid = decodedToken.getUid();
            String role = (String) decodedToken.getClaims().get("role");

            SecureDTO res = new SecureDTO("OK", uid, role);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            SecureDTO res = new SecureDTO("Error: " + e.getMessage(), null, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        }
    }
}
