package org.finalproject.spring.boolfilm.security;

import java.util.Optional;

import org.finalproject.spring.boolfilm.model.User;
import org.finalproject.spring.boolfilm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    // REPOSITORY
    @Autowired
    private UserRepository userRepository;

    // METHODS
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // find usernare
        Optional<User> optionalUser = userRepository.findByUsername(username);

        // if username doesn't exist, UsernameNotFoundException
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // else
        return new DatabaseUserDetails((optionalUser.get()));
    }

}
