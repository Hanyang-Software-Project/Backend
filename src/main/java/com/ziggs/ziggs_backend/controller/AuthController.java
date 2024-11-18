package com.ziggs.ziggs_backend.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.ziggs.ziggs_backend.dto.UserDTO;
import com.ziggs.ziggs_backend.entity.User;
import com.ziggs.ziggs_backend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO) {
        try {
            // Créer l'utilisateur dans Firebase avec email et mot de passe
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(userDTO.getEmail())
                    .setPassword("defaultPassword"); // À remplacer par une gestion sécurisée du mot de passe
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            // Synchroniser l'utilisateur avec la base de données PostgreSQL
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setEmail(userDTO.getEmail());
            user.setPassword("defaultPassword");
            user.setRole(User.Role.USER); // Par défaut, rôle utilisateur
            userRepository.save(user);

            return "Utilisateur créé avec succès : " + user.getUsername();
        } catch (Exception e) {
            return "Erreur lors de la création de l'utilisateur : " + e.getMessage();
        }
    }

    @GetMapping("/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        // Récupérer un utilisateur par email depuis PostgreSQL
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        return new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getCreatedAt());
    }
}


// WARNING EN DESSOUS CODE QUI FONCTIONNAIT AVANT FIREBASE

/*package com.ziggs.ziggs_backend.controller;
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
}*/
