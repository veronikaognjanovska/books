package com.lod.books.services;

import com.lod.books.model.User;

public interface AuthService {
    User login(String username, String password);
}
