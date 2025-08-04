package com.dyma.tennis.data;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "dyma_role", schema = "public")
public class RoleEntity {

    @Id
    @Column(name = "name", length = 50, nullable = false)
    private String name;
}
