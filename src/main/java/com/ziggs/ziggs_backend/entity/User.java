package com.ziggs.ziggs_backend.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Username is mandatory.")
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Email(message = "Invalid email.")
    @NotBlank(message = "Email is mandatory.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", length = 15, nullable = false, unique = true)
    @NotBlank(message = "Phone number is mandatory.")
    @Pattern(regexp = "^[+]?[0-9]{7,15}$", message = "Invalid phone number format.")
    private String phoneNumber;


    @NotBlank(message = "Password is mandatory.")
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

    @ManyToOne
    @JoinColumn(name = "house_id", nullable = false)
    private House house;


    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }


    public enum Role {
        USER,
        ADMIN
    }

}
