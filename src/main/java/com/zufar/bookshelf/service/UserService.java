package com.zufar.bookshelf.service;

import com.zufar.bookshelf.entity.Role;
import com.zufar.bookshelf.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserService {

    public static String ADMIN_LOGIN = "1";
    public static String USER_LOGIN = "2";
    static User ADMIN = new User("Alice", "Fithcer", ADMIN_LOGIN, "1", null);
    static User USER = new User("Katy", "stoty", USER_LOGIN, "2", null);
    public static Role ADMIN_ROLE = new Role(1L, "ROLE_ADMIN");
    public static Role USER_ROLE = new Role(2L, "ROLE_USER");

    public UserService() {
    }

    static {
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(USER_ROLE);
        USER.setRoles(userRoles);

        Set<Role> adminRoles = new HashSet<>();
        userRoles.add(ADMIN_ROLE);
        ADMIN.setRoles(adminRoles);
    }

    public static User getByLogin(String login) {
        if (ADMIN_LOGIN.equals(login)) {
            return ADMIN;
        } else if (USER_LOGIN.equals(login)) {
            return USER;
        } else {
            final String errorMessage = "Loading user details is impossible.";
            throw new UsernameNotFoundException(errorMessage);
        }
    }

}
