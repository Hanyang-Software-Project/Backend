package com.ziggs.ziggs_backend.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.ziggs.ziggs_backend.dto.UserIdDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "House")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String houseName;

    @NotNull
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    @JsonGetter("users")
    public List<UserIdDTO> getUserIds() {
        List<UserIdDTO> userIdDTOs = new ArrayList<>();
        if (users != null) {
            for (User user : users) {
                userIdDTOs.add(new UserIdDTO(user.getUserId()));
            }
        }
        return userIdDTOs;
    }
}
