package com.lemare.stock.controllers;

import com.lemare.stock.dtos.UserDto;
import com.lemare.stock.models.User;
import com.lemare.stock.services.Exceptions.DatabaseException;
import com.lemare.stock.services.Exceptions.ResourceNotFoundException;
import com.lemare.stock.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/users")
public class UserController {

    final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAllUsers() {
        List<User> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        User user = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @PostMapping
    public ResponseEntity<Object> newUser(@RequestBody User user) {
        user = service.saveUser(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody @Valid UserDto userDto){
        try {
            User user = service.findById(id);
            user.setName(userDto.getName());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setEmail(userDto.getEmail());

            return ResponseEntity.status(HttpStatus.OK).body(service.saveUser(user));
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") Long id) {
        try {
            User user = service.findById(id);
            service.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}
