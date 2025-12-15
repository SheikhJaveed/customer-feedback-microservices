package com.example.userservice.controller;

import com.example.userservice.dto.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService svc;

    public UserController(UserService svc) {
        this.svc = svc;
    }

    // public for demo - in real prod, protect appropriately
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(svc.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(svc.getUser(id));
    }

    // Only ADMIN would be allowed to create user normally; here it's open for testing.
    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody User input) {
        return ResponseEntity.ok(svc.createUser(input));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @RequestBody User input) {
        return ResponseEntity.ok(svc.updateUser(id, input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        svc.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
