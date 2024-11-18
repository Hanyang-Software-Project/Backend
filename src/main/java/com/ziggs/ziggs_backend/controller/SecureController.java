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
            // 1. Vérifie que l'en-tête contient un token
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return "Erreur : Token manquant ou invalide";
            }
            String idToken = authorizationHeader.substring(7); // Retire "Bearer " du token

            // 2. Valide l'ID Token avec Firebase
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);

            // 3. Récupère les informations nécessaires
            String uid = decodedToken.getUid(); // L'identifiant unique de l'utilisateur
            String role = (String) decodedToken.getClaims().get("role"); // Claim personnalisé

            // 4. Tu peux maintenant utiliser l'UID pour récupérer les infos dans ta base de données, si nécessaire
            return "Token valide pour l'utilisateur avec UID : " + uid + " et rôle : " + role;
        } catch (Exception e) {
            // 5. Gérer les erreurs si le token est invalide
            return "Erreur : " + e.getMessage();
        }
    }
}
