package com.ziggs.ziggs_backend.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class FirebaseAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Récupérer le token de l'en-tête Authorization
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            try {
                // Valider le token Firebase
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token.substring(7));
                String uid = decodedToken.getUid(); // UID de l'utilisateur

                // Ajouter l'utilisateur authentifié dans la requête pour un usage ultérieur
                request.setAttribute("uid", uid);
                request.setAttribute("claims", decodedToken.getClaims());
                return true;
            } catch (Exception e) {
                // Si le token est invalide, retourne une réponse 401
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token invalide : " + e.getMessage());
                return false;
            }
        }
        // Si le token est absent ou incorrect, retourne une réponse 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Token manquant ou invalide.");
        return false;
    }
}
