package com.example.app.service;

import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean register(User user) {
        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername())) {
            return false;
        }

        // Encrypt password before saving
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);
        return true;
    }

    public boolean login(String email, String rawPassword) {
        User user = userRepository.findByEmail(email);
        return user != null && passwordEncoder.matches(rawPassword, user.getPassword());
    }
    public void updateUser(User user) {
        userRepository.save(user);
    }
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username) != null;
    }


}
