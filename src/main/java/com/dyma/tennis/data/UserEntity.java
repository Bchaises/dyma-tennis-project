package com.dyma.tennis.data;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "dyma_user", schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "login", length = 50, nullable = false)
    private String login;

    @Column(name = "password",  length = 50, nullable = false)
    private String password;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "first_name",  length = 50, nullable = false)
    private String firstName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dyma_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
