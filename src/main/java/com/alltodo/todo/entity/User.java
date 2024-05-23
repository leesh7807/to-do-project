package com.alltodo.todo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User {
    @Id
    @UuidGenerator
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_method")
    private LoginMethod loginMethod;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todos;

    @Builder
    public User(String email, String password, LoginMethod loginMethod) {
        this.email = email;
        this.password = password;
        this.loginMethod = loginMethod;
        todos = new ArrayList<>();
    }
}
