package com.joselara.crmusers.services;

import com.joselara.crmusers.models.User;
import com.joselara.crmusers.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private static final Logger LOG = LogManager.getLogger(AuthenticationService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(final Authentication authentication) {

        LOG.trace("Checking if the authentication request is valid");
        if (isInvalidAuthentication(authentication)) {
            return null;
        }

        LOG.trace("Retrieving the user related to that authentication");
        final Optional<User> optionalUser = userRepository.findById(authentication.getName());

        LOG.trace("Retrieving the authentication");
        Authentication authenticatedUser = getAuthentication(authentication, optionalUser);

        LOG.trace("Returning the authentication if it is not null");
        if (authenticatedUser != null) {
            return authenticatedUser;
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Authentication getAuthentication(Authentication authentication, Optional<User> optionalUser) {
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String providedUserEmail = authentication.getName();
            Object providedUserPassword = authentication.getCredentials();

            if (providedUserEmail.equalsIgnoreCase(user.getEmail()) && providedUserPassword.equals(user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),
                        Collections.singleton(new SimpleGrantedAuthority(user.getUserRole().name())));
            }
        }
        return null;
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
