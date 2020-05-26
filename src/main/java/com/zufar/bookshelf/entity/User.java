package com.zufar.bookshelf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min=2, max=40)
    @Column(nullable = false)
    private String fullName;

    @NotNull
    @Size(min=2, max=40)
    @Column(nullable = false)
    private String nickName;

    @NotNull
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull
    @Size(min=2, max=40)
    @Column(nullable = false)
    private String login;

    @NotNull
    @Size(min=2, max=50)
    @Column(nullable = false)
    private String password;

    @NotNull
    @OneToMany
    private Set<Role> roles;

    public void addRole(Role role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        if (this.roles.contains(role)) {
            return;
        }
        this.roles.add(role);
    }
}


