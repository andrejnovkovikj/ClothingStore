package com.example.store.service;

import com.example.store.model.User;

public interface AuthService {
    User login(String username, String password);
}