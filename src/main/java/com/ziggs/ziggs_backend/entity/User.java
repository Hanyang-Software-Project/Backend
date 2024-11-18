package com.ziggs.ziggs_backend.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotBlank(message = "Le nom d'utilisateur est obligatoire.")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Email(message = "Email invalide.")
    @NotBlank(message = "L'email est obligatoire.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 50)
    private Role role = Role.USER;;

    @OneToMany(mappedBy = "user")
    private List<FeedbackTicket> feedbackTickets;

    @OneToMany(mappedBy = "user")
    private List<Alert> alerts;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    public enum Role {
        USER,
        ADMIN
    }

}
