package com.Sintad_test.config.jwt;

import com.Sintad_test.config.interfaces.JwtProviderMethods;
import com.Sintad_test.config.interfaces.TokenProvider;
import com.Sintad_test.config.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTTokenFilter extends OncePerRequestFilter implements TokenProvider {

    private static final Logger log = LoggerFactory.getLogger(JWTTokenFilter.class);

    private final JwtProviderMethods jwtProviderMethods;
    private final UserDetailsService userDetailsService;

    public JWTTokenFilter(JwtProviderMethods jwtProviderMethods, UserDetailsService userDetailsService) {
        this.jwtProviderMethods = jwtProviderMethods;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(req);
            if (token != null && jwtProviderMethods.validateToken(token)) {
                String username = jwtProviderMethods.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            log.error(String.format("Errores en doFilter: %s", e.getMessage()));
        }
        filterChain.doFilter(req, res);
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer"))
            return header.replace("Bearer ", "");
        return null;
    }
}
