package com.kref.K.Ref.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String name;
    @Column(
            unique = true,
            nullable = false
    )
    private String email;
    @Column(
            unique = true,
            nullable = false
    )
    private String phoneNo;
    private String password;
    private boolean enabled = false;
    private String role = "user";
}
