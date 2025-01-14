package com.Sintad_test.config.controller;

import com.Sintad_test.config.interfaces.JwtProviderMethods;
import com.Sintad_test.config.models.JwtDto;
import com.Sintad_test.config.models.UserDetailsImpl;
import com.Sintad_test.users.interfaces.UserMapEntityToDto;
import com.Sintad_test.users.models.entities.User;
import com.Sintad_test.users.models.request.LoginRequest;
import com.Sintad_test.users.models.response.UserResponse;
import com.Sintad_test.utils.Response;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtProviderMethods jwtProviderMethods;
    private final UserMapEntityToDto userMapper;

    public AuthController(AuthenticationManager authenticationManager, JwtProviderMethods jwtProvider, UserMapEntityToDto  userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtProviderMethods = jwtProvider;
        this.userMapper = userMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof UserDetailsImpl) {
            User user = ((UserDetailsImpl) auth.getPrincipal()).getUser();
            return userMapper.entityToDto(user);
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest ) {

        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtProviderMethods.generateToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            JwtDto jwtDto = new JwtDto(getUser(), jwt, userDetails.getAuthorities());
            return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new Response("Usuario o contraseña incorrectos", 400), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>(new Response("Sesión cerrada con éxito", 200), HttpStatus.OK);
    }
}
