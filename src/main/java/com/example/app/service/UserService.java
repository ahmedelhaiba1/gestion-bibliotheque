package com.example.app.service;



import com.example.app.model.User;
import com.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean register(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false; // utilisateur existe déjà
        }
        // Ici tu peux hasher le mot de passe avant de sauvegarder (recommandé)
        userRepository.save(user);
        return true;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) return false;
        // Vérifier mot de passe (simple égalité ici, pour commencer)
        return user.getPassword().equals(password);
    }
}
