package com.joselara.crmusers.services;

import com.joselara.crmusers.models.User;
import com.joselara.crmusers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationService implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(final Authentication authentication) {

        if (isInvalidAuthentication(authentication)) {
            return null;
        }

        final Optional<User> optionalUser = this.userRepository.findById(authentication.getName());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String providedUserEmail = authentication.getName();
            Object providedUserPassword = authentication.getCredentials();

            if (providedUserEmail.equalsIgnoreCase(user.getEmail()) && providedUserPassword.equals(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(user.getUserRole().name())));
            }
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean isInvalidAuthentication(Authentication authentication) {
        return authentication.getName() == null || authentication.getName().isEmpty() ||
                authentication.getCredentials() == null || authentication.getCredentials().toString().isEmpty();
    }
}
