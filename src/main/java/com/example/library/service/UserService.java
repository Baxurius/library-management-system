package com.example.library.service;

import com.example.library.model.User;
import com.example.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }


    public User save(User user) {
        user.setRole("STUDENT");
        return repository.save(user);
    }

    public User changeRole(Long userId, String newRole) {
        User user = findById(userId);
        if (user != null) {
            user.setRole(newRole.toUpperCase());
            return repository.save(user);
        }
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username).orElse(null);
    }
}
