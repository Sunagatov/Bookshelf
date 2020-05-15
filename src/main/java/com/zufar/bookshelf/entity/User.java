package com.zufar.bookshelf.entity;

import java.util.Set;
import lombok.Data;

@Data
public class User {

    private String name;
    private String email;
    private String login;
    private String password;
    private Set<Role> roles;
}
