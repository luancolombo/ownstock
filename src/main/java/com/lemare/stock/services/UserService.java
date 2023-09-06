package com.lemare.stock.services;

import com.lemare.stock.models.User;
import com.lemare.stock.repositories.UserRepository;
import com.lemare.stock.services.Exceptions.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public User saveUser(User user) {
        return repository.save(user);
    }
    public List<User> findAll() {
        return repository.findAll();
    }
    public User findById(Long id) {
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    @Transactional
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}

