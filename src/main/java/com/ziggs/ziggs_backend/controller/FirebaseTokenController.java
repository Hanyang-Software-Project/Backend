package com.ziggs.ziggs_backend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FirebaseTokenController {

    @GetMapping("/generate-token")
    public String generateCustomToken(@RequestParam String uid) {
        try {
            // Ajouter des claims personnalisés, si nécessaire
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", "user");

            // Générer un token personnalisé pour l'UID donné
            String customToken = FirebaseAuth.getInstance().createCustomToken(uid, claims);

            return "Token généré : " + customToken;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Erreur lors de la génération du token : " + e.getMessage();
        }
    }
}
