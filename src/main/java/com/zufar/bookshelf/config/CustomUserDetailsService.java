package com.zufar.bookshelf.config;

import com.zufar.bookshelf.entity.CustomUserDetails;
import com.zufar.bookshelf.entity.User;
import com.zufar.bookshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final User user;
        try {
            user = UserService.getByLogin(login);
        } catch (Exception exc) {
            final String errorMessage = "Loading user details is impossible.";
            throw new UsernameNotFoundException(errorMessage, exc);
        }
        return new CustomUserDetails(
                user.getRoles(),
                passwordEncoder.encode(user.getPassword()),
                user.getName(),
                true,
                true,
                true,
                true
        );
    }
}