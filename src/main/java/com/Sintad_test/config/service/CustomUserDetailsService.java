package com.Sintad_test.config.service;

import com.Sintad_test.config.models.UserDetailsImpl;
import com.Sintad_test.users.interfaces.GetUserData;
import com.Sintad_test.users.models.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final GetUserData getUserData;

    public CustomUserDetailsService(GetUserData userServiceImpl) {
        this.getUserData = userServiceImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserData.getUserByUsername(email);
        if (user == null) {
            throw new UsernameNotFoundException("El usuario no se encuentra registrado");
        }

        return new UserDetailsImpl(user);
    }
}
