package com.lod.books.services.impl;

import com.lod.books.model.User;
import com.lod.books.model.exceptions.InvalidArgumentsExceptions;
import com.lod.books.model.exceptions.InvalidUserCredentialsException;
import com.lod.books.repository.UserRepository;
import com.lod.books.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsExceptions();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }


}
