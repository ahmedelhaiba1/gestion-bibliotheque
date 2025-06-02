package com.example.app.repository;


import com.example.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    void delete(User user);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);


}
